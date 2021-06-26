package com.WebMall.controller;

import com.WebMall.model.Store;
import com.WebMall.service.storeServices.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    /**
     * Отображение страницы магазина по Id
     */
    @RequestMapping("/showStore")
    public String showStorePage(@RequestParam(name = "storeId") Long storeId){
        Store requestedStore = storeService.findStoreById(storeId);

        return "store-dashboard";
    }

    @RequestMapping("/myStore")
    public String showStoreArea(@RequestParam(name = "storeId") Long storeId){

         return "my-store";
    }
}
