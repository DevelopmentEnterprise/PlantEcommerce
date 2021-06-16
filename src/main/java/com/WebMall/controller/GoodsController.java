package com.WebMall.controller;

import com.WebMall.model.Good;
import com.WebMall.service.goodsServices.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping
    public String goods(Model model, @RequestParam(name = "categoryName", required = false) String categoryName,
                        @RequestParam(name = "pageNum", required = false) Integer pageNum,
                        @RequestParam(name = "sortBy", required = false) String sortType){

        List<Good> goodsToShow = goodsService.getGoodsByRequestParams(categoryName, pageNum, sortType);

        model.addAttribute("goodsToShow", goodsToShow);
        model.addAttribute("pagersCount", 1);
        String categoryUI = "";

        categoryUI = switch (categoryName){
            case "discount"  -> "Товары по скидке";
            case "hits" -> "Хиты продаж";
            case "recommended" -> "Рекомендуемые";
            default -> categoryName;
        };

        model.addAttribute("categoryUI", categoryUI);
        model.addAttribute("categoryName", categoryName);

        return "productPage";
    }
}
