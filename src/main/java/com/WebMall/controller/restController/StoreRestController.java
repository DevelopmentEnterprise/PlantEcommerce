package com.WebMall.controller.restController;

import com.WebMall.model.*;
import com.WebMall.model.enums.Status;
import com.WebMall.repository.CouponRepository;
import com.WebMall.repository.GoodImageRepository;
import com.WebMall.repository.OrderRepository;
import com.WebMall.service.AccessDeniedException;
import com.WebMall.service.NotFoundException;
import com.WebMall.service.goodsServices.GoodsService;
import com.WebMall.service.storeServices.StoreService;
import com.WebMall.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * -- Made by Killer_hacker999 --
 * API for frontend calls to dynamic data load (no page refresh)
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/store")
public class StoreRestController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodImageRepository goodImageRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CouponRepository couponRepository;

    /**
     * @param goodsFilter filter name (can be dynamic -> new elem must be added to goodsService
     *                    Returns goods that are filtered by a particular param
     */
    @RequestMapping(value = "/getGoodsByFilter", method = RequestMethod.GET)
    public List<Good> getGoodsByFilter(@RequestParam(name = "goodsFilter") String goodsFilter){
        //Check for authorization params
        if (!userService.checkAuthUser() || !userService.checkForRole("ROLE_SELLER")){
            throw new AccessDeniedException();
        }

        Store requestedStore = userService.getLoggedUser().getStore();

        return storeService.filterStoreGoods(requestedStore.getGoods(), goodsFilter);
    }

    /**
     * @param ordersFilter order filter (order statuses)
     *                    Returns orders that are filtered by order status
     */
    @RequestMapping("/getOrdersByFilter")
    public List<Order> getOrdersByFilter(@RequestParam(name = "ordersFilter") String ordersFilter){
        //Check for authorization params
        if (!userService.checkAuthUser() || !userService.checkForRole("ROLE_SELLER"))
            throw new AccessDeniedException();

        Store requestedStore = userService.getLoggedUser().getStore();

        return storeService.filterStoreOrders(requestedStore.getOrders(), ordersFilter);
    }

    /**
     * Get all goods in store (accessible for every user)
     */
    @RequestMapping("/getAllStoreGoods")
    public List<Good> getAllStoreGoods(){
        User loggedUser = userService.getLoggedUser();
        if (loggedUser == null) return new ArrayList<>();

        return loggedUser.getStore().getGoods();
    }

    /**
     * @param goodId id of good to remove from DB
     *               Removes good from database
     */
    @RequestMapping("/deleteGoodById")
    public byte deleteGoodById(@RequestParam(value = "goodId") Long goodId){
        //Check for authorization params
        if (!userService.checkAuthUser() || !userService.checkForRole("ROLE_SELLER")){
            throw new AccessDeniedException();
        }

        //Find good in db
        Good good = goodsService.getGoodById(goodId);
        if (good == null) return -1;

        goodsService.deleteGood(good);
        return 1;
    }

    /**
     * @param goodImageId Id of image that musy be removed
     * @return
     */
    @RequestMapping("/deleteGoodImageById")
    public byte deleteGoodImageById(@RequestParam("goodImageId") Long goodImageId){
        //Check for authorization params
        if (!userService.checkAuthUser() || !userService.checkForRole("ROLE_SELLER")) throw new AccessDeniedException();;

        Optional<GoodImage> imgToDel = goodImageRepository.findById(goodImageId);
        if (imgToDel.isEmpty()) throw new AccessDeniedException();

        goodImageRepository.delete(imgToDel.get());
        return 1;
    }

    /**
     * @param orderId Id of order that must have status canceled
     */
    @RequestMapping("/cancelOrder")
    public byte cancelOrderByOrderId(@RequestParam("orderId") Long orderId){
        //Check for authorization params
        if (!userService.checkAuthUser() || !userService.checkForRole("ROLE_SELLER"))
            throw new AccessDeniedException();

        Store userStore = userService.getLoggedUser().getStore();
        List<Order> currentStoreOrders = userStore.getOrders();

        boolean hasRequestedOrder = currentStoreOrders
                .stream()
                .anyMatch(el -> el.getId().equals(orderId));

        if (!hasRequestedOrder) throw new AccessDeniedException();

        Optional<Order> requestedOrder = orderRepository.findById(orderId);
        if (requestedOrder.isEmpty()) throw new NotFoundException();

        Order currentOrder = requestedOrder.get();
        currentOrder.setStatus(Status.CANCELED);
        orderRepository.save(currentOrder);

        return 1;
    }

    /**
     * @param orderStatusId index of Status enum
     */
    @RequestMapping("/saveOrder")
    public byte saveOrderByOrderId(@RequestParam("orderId") Long orderId,
                                   @RequestParam("orderStatusId") int orderStatusId){
        //Check for authorization params
        if (!userService.checkAuthUser() || !userService.checkForRole("ROLE_SELLER"))
            throw new AccessDeniedException();

        if (orderStatusId < 0) throw new AccessDeniedException();

        Store userStore = userService.getLoggedUser().getStore();
        List<Order> currentStoreOrders = userStore.getOrders();

        boolean hasRequestedOrder = currentStoreOrders
                .stream()
                .anyMatch(el -> el.getId().equals(orderId));

        if (!hasRequestedOrder) throw new AccessDeniedException();

        Optional<Order> requestedOrder = orderRepository.findById(orderId);
        if (requestedOrder.isEmpty()) throw new NotFoundException();

        Order currentOrder = requestedOrder.get();
        currentOrder.setStatus(Status.values()[orderStatusId]);
        orderRepository.save(currentOrder);

        return 1;
    }

    /**
     * POST Request to add new coupon for store
     */
    @ResponseBody
    @RequestMapping(value = "/addCoupon", method = RequestMethod.POST)
    public byte addStoreCoupon(@RequestBody Coupon addedCoupon){
        //Check for authorization params
        if (!userService.checkAuthUser() || !userService.checkForRole("ROLE_SELLER"))
            throw new AccessDeniedException();

        if (addedCoupon.getCoupon() == null || addedCoupon.getDiscount() == null || addedCoupon.getExpiredDate() == null)
            return -1;

        Store currentStore = userService.getLoggedUser().getStore();
        addedCoupon.setStore(currentStore);

        couponRepository.save(addedCoupon);
        return 1;
    }
}
