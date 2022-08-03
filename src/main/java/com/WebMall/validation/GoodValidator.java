package com.WebMall.validation;

import com.WebMall.model.Good;
import org.springframework.stereotype.Component;

@Component
public class GoodValidator {
    public boolean validateGood(Good good){
        if(good.getName() != null && good.getDescription() != null){
            return !good.getName().trim().equals("") && !good.getDescription().trim().equals("");
        }
        return false;
    }
}
