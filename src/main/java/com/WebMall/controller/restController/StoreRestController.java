package com.WebMall.controller.restController;

import com.WebMall.model.*;
import com.WebMall.repository.GoodImageRepository;
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

        User loggedUser = userService.getLoggedUser();
        if (loggedUser == null) return new ArrayList<>();

        Store requestedStore = loggedUser.getStore();

        return storeService.filterStoreGoods(requestedStore.getGoods(), goodsFilter);
    }

    /**
     * @param ordersFilter order filter (order statuses)
     *                    Returns orders that are filtered by order status
     */
    @RequestMapping("/getOrdersByFilter")
    public List<Order> getOrdersByFilter(@RequestParam(name = "ordersFilter") String ordersFilter){
        //Check for authorization params
        if (!userService.checkAuthUser() || !userService.checkForRole("ROLE_SELLER")){
            throw new AccessDeniedException();
        }

        User loggedUser = userService.getLoggedUser();
        if (loggedUser == null) return new ArrayList<>();

        Store requestedStore = loggedUser.getStore();

        return storeService.filterStoreOrders(requestedStore.getOrders(), ordersFilter);
    }

    @RequestMapping("/getAllStoreGoods")
    public List<Good> getAllStoreGoods(){
        User loggedUser = userService.getLoggedUser();
        if (loggedUser == null) return new ArrayList<>();

        return loggedUser.getStore().getGoods();
    }

    @RequestMapping("/deleteGoodById")
    public byte deleteGoodById(@RequestParam(value = "goodId") Long goodId){
        //Check for authorization params
        if (!userService.checkAuthUser() || !userService.checkForRole("ROLE_SELLER")){
            throw new NotFoundException();
        }

        //Find good in db
        Good good = goodsService.getGoodById(goodId);
        if (good == null) return -1;

        goodsService.deleteGood(good);
        return 1;
    }

    @RequestMapping("/deleteGoodImageBy")
    public byte deleteGoodImageById(@RequestParam("goodImageId") Long goodImageId){
        //Check for authorization params
        if (!userService.checkAuthUser() || !userService.checkForRole("ROLE_SELLER")) throw new NotFoundException();;

        Optional<GoodImage> imgToDel = goodImageRepository.findById(goodImageId);
        if (imgToDel.isEmpty()) throw new AccessDeniedException();

        goodImageRepository.delete(imgToDel.get());
        return 1;
    }
}
