package com.WebMall.controller.restController;

import com.WebMall.model.CartItem;
import com.WebMall.model.Good;
import com.WebMall.model.User;
import com.WebMall.repository.CartItemRepository;
import com.WebMall.repository.GoodRepository;
import com.WebMall.service.AccessDeniedException;
import com.WebMall.service.NotFoundException;
import com.WebMall.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/customer")
public class CustomerRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private GoodRepository goodRepository;

    @RequestMapping("/getCustomerCart")
    public List<CartItem> getCustomerCart(){
        if (!userService.checkAuthUser())
            throw new AccessDeniedException();

        return userService.getLoggedUser().getCart();
    }

    @RequestMapping(value = "/getCartGoods", method = RequestMethod.POST)
    public List<Good> getGoodsByCart(@RequestBody List<Long> goodIds){
        List<Good> goods = new ArrayList<>();

        for(long goodId : goodIds){
            Optional<Good> foundGood = goodRepository.findById(goodId);
            foundGood.ifPresent(goods::add);
        }

        for(Good good : goods){
            good.setStore(null);
            good.setGoodOptions(null);
            good.setGoodCategories(null);
            good.setReviews(null);
        }

        return goods;
    }

    /**
     * @param goodId id of Good entity that was added to cart
     * @param quantity  quantity of goods in cart
     */
    @RequestMapping("/addGoodToCart")
    public boolean addGoodToCart(@RequestParam("goodId") Long goodId,
                                 @RequestParam("quantity") Integer quantity){
        //Check authorization
        if(!userService.checkAuthUser()) throw new AccessDeniedException();

        User loggedUser = userService.getLoggedUser();
        List<CartItem> customerCart = loggedUser.getCart();
        Optional<Good> requestedGood = goodRepository.findById(goodId);

        if (requestedGood.isEmpty())
            throw new NotFoundException();

        if (customerCart == null)
            customerCart = new ArrayList<>();

        Optional<CartItem> requestedCartItem = cartItemRepository
                .findAll()
                .stream()
                .filter(el -> el.getGood().getId().equals(goodId))
                .findFirst();

        if (requestedCartItem.isEmpty()){
            CartItem newCartItem = new CartItem();
            newCartItem.setQuantity(quantity);
            newCartItem.setGood(requestedGood.get());
            newCartItem.setCustomer(loggedUser);

            customerCart.add(newCartItem);
        }else{
            CartItem existingCartItem = requestedCartItem.get();
            existingCartItem.setQuantity(quantity);
        }

        loggedUser.setCart(customerCart);

        return userService.save(loggedUser);
    }

    @RequestMapping("/deleteCartGood")
    public void deleteCartGood(@RequestParam("goodId") Long goodId){
        if (!userService.checkAuthUser())
            throw new AccessDeniedException();

        User loggedUser = userService.getLoggedUser();

        Optional<CartItem> cartItemToDelete = loggedUser
                .getCart()
                .stream()
                .filter(el -> el.getGood().getId().equals(goodId))
                .findFirst();

        if (cartItemToDelete.isEmpty())
            throw new NotFoundException();

        loggedUser.getCart().removeIf(el -> el.getId().equals(cartItemToDelete.get().getId()));

        cartItemRepository.deleteById(cartItemToDelete.get().getId());
        userService.save(loggedUser);
    }
}
