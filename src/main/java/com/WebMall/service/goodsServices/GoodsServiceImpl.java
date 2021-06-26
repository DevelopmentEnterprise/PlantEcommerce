package com.WebMall.service.goodsServices;

import com.WebMall.model.Good;
import com.WebMall.model.GoodCategory;
import com.WebMall.model.enums.SortType;
import com.WebMall.repository.CategoryRepository;
import com.WebMall.repository.GoodRepository;
import com.WebMall.validation.GoodValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GoodValidator goodValidator;

    private boolean checkItemsExist(Object item){
        if (item == null) return false;

        if (item instanceof Collection){
            return ((Collection) item).size() != 0;
        }

        return false;
    }

    private List<Good> limitGoodsByPageNum(List<Good> goods, Integer pageNum){
        if (pageNum == null || pageNum == 1 || 35*pageNum+36 >= goods.size())
            return goods.stream().limit(35).collect(Collectors.toList());

        return goods.subList(35 * pageNum + 1, 35 * pageNum + 36);
    }

    @Override
    public int getPagersCountRequired(int allGoodsCount, int goodsToShowCount) {
        return (int) Math.ceil((allGoodsCount - goodsToShowCount) / 35) + 1;
    }

    @Override
    public SortType getSortType(String sortType){
        return SortType.fromString(sortType);
    }

    @Override
    public List<Good> getGoodsByRequestParams(String categoryName, Integer pageNum, String sortBy) {
        List<Good> goodsToShow = new ArrayList<>();

        if (categoryName != null){
            switch (categoryName){
                case "discount" -> {
                    List<Good> goodsWithDiscount = (List<Good>) getGoodsWithDiscount();
                    goodsToShow = limitGoodsByPageNum(goodsWithDiscount, pageNum);
                }

                case "hits" -> {
                    List<Good> foundGoods = (List<Good>) getHitGoods();
                    goodsToShow = limitGoodsByPageNum(foundGoods, pageNum);
                }

                case "recommended" -> {
                    List<Good> foundGoods = (List<Good>) getRecommendedGoods();
                    goodsToShow = limitGoodsByPageNum(foundGoods, pageNum);
                }
                default -> {
                    List<Good> foundGoods = (List<Good>) getGoodsByCategory(categoryName);
                    goodsToShow = limitGoodsByPageNum(foundGoods, pageNum);
                }
            }
        }

        if (sortBy != null){
            SortType sortType = getSortType(sortBy);

            if (sortType != null)
            switch (sortType){
                case POPULARITY -> goodsToShow = (List<Good>) sortGoodsByOrdersCount(goodsToShow);
                case RATING -> goodsToShow = (List<Good>) sortGoodsByRating(goodsToShow);
                case PRICE -> goodsToShow = (List<Good>) sortGoodsByPrice(goodsToShow);
                case DISCOUNT -> goodsToShow = (List<Good>) sortGoodsByDiscount(goodsToShow);
                case NAME -> goodsToShow = (List<Good>) sortGoodsByName(goodsToShow);
            }
        }

        return goodsToShow;
    }

    @Override
    public Collection<Good> getGoodsByCategory(String categoryName) {
        if (categoryName == null || categoryName.length() == 0) return null;

        GoodCategory requiredCategory = categoryRepository.findByName(categoryName);
        return requiredCategory.getGoods();
    }

    @Override
    public Good getGoodById(Long goodId) {
        Optional<Good> found = goodRepository.findById(goodId);
        return found.orElse(null);
    }

    @Override
    public Collection<Good> getGoodsWithDiscount() {
        return goodRepository.findAll()
                .stream()
                .filter(el -> el.getPriceBeforeDiscount() != null)
                .sorted(Comparator.comparing(Good::getOrdersCount).reversed())
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

    @Override
    public Collection<Good> getRecommendedGoods() {
        return goodRepository.findAll().stream()
                .filter(el -> el.getRating() >= 4.5 && el.getOrdersCount() > 10)
                .sorted(Comparator.comparing(Good::getRating).thenComparing(Good::getOrdersCount).reversed())
                .collect(Collectors.toList());
    }

    //Sort goods by parameters
    private Collection<Good> sortGoodsByDiscount(Collection<Good> goodsToSort) {
        if (!checkItemsExist(goodsToSort)) return null;

        return goodsToSort.stream()
                .filter(el -> el.getPriceBeforeDiscount() != null)
                .sorted(Comparator.comparing(Good::getPriceBeforeDiscount).reversed())
                .collect(Collectors.toList());
    }

    private Collection<Good> sortGoodsByName(Collection<Good> goodsToSort) {
        if (!checkItemsExist(goodsToSort)) return null;

        return goodsToSort.stream()
                .sorted(Comparator.comparing(Good::getName))
                .collect(Collectors.toList());
    }

    private Collection<Good> sortGoodsByOrdersCount(Collection<Good> goodsToSort) {
        if (!checkItemsExist(goodsToSort)) return null;

        return goodsToSort.stream()
                .filter(el -> el.getOrdersCount() != null)
                .sorted(Comparator.comparing(Good::getOrdersCount).reversed())
                .collect(Collectors.toList());
    }

    private Collection<Good> sortGoodsByWishesCount(Collection<Good> goodsToSort) {
        if (!checkItemsExist(goodsToSort)) return null;

        return goodsToSort.stream()
                .filter(el -> el.getWishesCount() != null)
                .sorted(Comparator.comparing(Good::getWishesCount).reversed())
                .collect(Collectors.toList());
    }

    private Collection<Good> sortGoodsByCartCount(Collection<Good> goodsToSort) {
        if (!checkItemsExist(goodsToSort)) return null;

        return goodsToSort.stream()
                .filter(el -> el.getCartCount() != null)
                .sorted(Comparator.comparing(Good::getCartCount).reversed())
                .collect(Collectors.toList());
    }

    private Collection<Good> sortGoodsByRating(Collection<Good> goodsToSort) {
        if (!checkItemsExist(goodsToSort)) return null;

        return goodsToSort.stream()
                .filter(el -> el.getRating() != null)
                .sorted(Comparator.comparing(Good::getRating).reversed())
                .collect(Collectors.toList());
    }

    private Collection<Good> sortGoodsByPrice(Collection<Good> goodsToSort) {
        if (!checkItemsExist(goodsToSort)) return null;

        return goodsToSort.stream()
                .sorted(Comparator.comparing(Good::getPrice))
                .collect(Collectors.toList());
    }
}
