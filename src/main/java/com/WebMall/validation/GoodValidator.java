package com.WebMall.validation;

import com.WebMall.model.Good;
import org.springframework.stereotype.Component;

@Component
public class GoodValidator {
    public boolean validateGood(Good good){
        if(good.getName() != null && good.getDescription() != null
                && good.getOnSale() != null){
            if(!good.getName().trim().equals("") && !good.getDescription().trim().equals(""))
                return true;
            else return false;
        }else
            return false;
    }
}
