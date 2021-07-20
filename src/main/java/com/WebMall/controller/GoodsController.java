package com.WebMall.controller;

import com.WebMall.model.Good;
import com.WebMall.service.NotFoundException;
import com.WebMall.service.goodsServices.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * -- Created by Killer_hacker999 --
 * Represents all functionality for operating goods logic
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * @return goods page filtered and sorted by params (used for good categories,
     * special types like discount...)
     */
    @RequestMapping
    public String goods(Model model, @RequestParam(name = "categoryName") String categoryName,
                        @RequestParam(name = "pageNum", required = false) Integer pageNum,
                        @RequestParam(name = "sortBy", required = false) String sortType){

        List<Good> goodsToShow = goodsService.getGoodsByRequestParams(categoryName, pageNum, sortType);
        int pagersRequired = goodsService.getPagersCountRequired(goodsToShow.size());

        model.addAttribute("goodsToShow", goodsToShow);
        model.addAttribute("pagersCount", pagersRequired);
        String categoryUI = "";

        categoryUI = switch (categoryName){
            case "discount"  -> "Товары по скидке";
            case "hits" -> "Хиты продаж";
            case "recommended" -> "Рекомендуемые";
            default -> categoryName;
        };

        model.addAttribute("categoryUI", categoryUI);
        model.addAttribute("categoryName", categoryName);

        return "goodsPage";
    }

    /**
     * Shows good card by id
     */
    @RequestMapping("/{goodId}")
    public String showGood(@PathVariable Long goodId, Model model){
        Good foundGood = goodsService.getGoodById(goodId);

        if (foundGood == null) {
            throw new NotFoundException();
        }

        model.addAttribute("good", foundGood);

        return "good-page";
    }
}
