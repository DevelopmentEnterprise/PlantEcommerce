package com.WebMall.controller.restController;

import com.WebMall.model.GoodCategory;
import com.WebMall.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class MainRestController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(path = "/getCategories",produces = "application/json")
    public List<GoodCategory> getAllGoodCategories(){
        return categoryRepository.findAll();
    }
}
