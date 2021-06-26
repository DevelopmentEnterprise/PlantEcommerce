package com.WebMall.controller;

import com.WebMall.model.Good;
import com.WebMall.service.goodsServices.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping
    public String index(Model model){
        List<Good> goodsWithDiscount = (List<Good>) goodsService.getGoodsWithDiscount();
        List<Good> goodsHits = (List<Good>) goodsService.getHitGoods();
//        List<Good> randomGoodsWithDiscount = new ArrayList<>();
//
//        for (int i = 0; i < goodsWithDiscount.size(); i++) {
//            int randIndex = random.nextInt(goodsWithDiscount.size());
//            //goodsWithDiscount.get(randIndex).getPriceDiscount()
//            randomGoodsWithDiscount.add(goodsWithDiscount.get(randIndex));
//        }

        model.addAttribute("goodsDiscount", goodsWithDiscount);
        model.addAttribute("goodsHits", goodsHits);

        return "index";
    }

    @RequestMapping("/test")
    public void test(){

    }
}
