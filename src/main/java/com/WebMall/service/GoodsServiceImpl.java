package com.WebMall.service;

import com.WebMall.model.Good;
import com.WebMall.repository.GoodRepository;
import com.WebMall.validation.GoodValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private GoodValidator goodValidator;

    //Get goods by parameters
    @Override
    public Good getGoodById(Long goodId) {
        Optional<Good> found = goodRepository.findById(goodId);
        return found.orElse(null);
    }

    @Override
    public Collection<Good> getGoodsWithDiscount() {
        return goodRepository.findAll()
                .stream()
                .filter(el -> el.getDiscount() != null)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Good> getHitGoods() {
        return goodRepository.findAll()
                .stream()
                .filter(el -> el.getRating() >= 4.5)
                .collect(Collectors.toList());
    }

    @Override
    public boolean addNewGood(Good good) {
        if (good == null) return false;

        boolean validateRes = goodValidator.validateGood(good);

        if (validateRes) {
            goodRepository.save(good);
            return true;
        }else
            return false;
    }

    @Override
    public boolean removeGoodById(Long goodId) {
        Good foundGood = getGoodById(goodId);

        if (foundGood != null){
            goodRepository.deleteById(goodId);
            return true;
        }

        return false;
    }

    @Override
    public boolean editGood(Good goodToEdit) {
        if(goodToEdit == null) return false;

        boolean isValid = goodValidator.validateGood(goodToEdit);
        if (isValid){
            goodRepository.save(goodToEdit);
            return true;
        }

        return false;
    }

    //Sort goods by parameters
    @Override
    public Collection<Good> sortGoodsByDiscount(Collection<Good> goodsToSort) {
        if (goodsToSort == null || goodsToSort.size() == 0) return null;

        return goodsToSort.stream()
                .filter(el -> el.getDiscount() != null)
                .sorted(Comparator.comparing(Good::getDiscount).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Good> sortGoodsByName(Collection<Good> goodsToSort) {
        if (goodsToSort == null || goodsToSort.size() == 0) return null;

        return goodsToSort.stream()
                .sorted(Comparator.comparing(Good::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Good> sortGoodsByOrdersCount(Collection<Good> goodsToSort) {
        if (goodsToSort == null || goodsToSort.size() == 0) return null;

        return goodsToSort.stream()
                .filter(el -> el.getOrdersCount() != null)
                .sorted(Comparator.comparing(Good::getOrdersCount).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Good> sortGoodsByWishesCount(Collection<Good> goodsToSort) {
        if (goodsToSort == null || goodsToSort.size() == 0) return null;

        return goodsToSort.stream()
                .filter(el -> el.getWishesCount() != null)
                .sorted(Comparator.comparing(Good::getWishesCount).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Good> sortGoodsByCartCount(Collection<Good> goodsToSort) {
        if (goodsToSort == null || goodsToSort.size() == 0) return null;

        return goodsToSort.stream()
                .filter(el -> el.getCartCount() != null)
                .sorted(Comparator.comparing(Good::getCartCount).reversed())
                .collect(Collectors.toList());
    }
}
