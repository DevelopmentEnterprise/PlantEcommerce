package com.WebMall.service.goodsServices;

import com.WebMall.model.Good;
import com.WebMall.model.enums.SortType;
import com.WebMall.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodRepository goodRepository;

    @Override
    public int getPagersCountRequired(int allGoodsCount) {
        return (int) Math.ceil(allGoodsCount/35) + 1;
    }

    @Override
    public SortType getSortType(String sortType){
        return SortType.fromString(sortType);
    }

    @Override
    public List<Good> getGoodsByRequestParams(String categoryName, Integer pageNum, String sortBy) {
        List<Good> goodsToShow = new ArrayList<>();

        //Check category specification is set
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
                    List<Good> foundGoods = (List<Good>) getGoodsByUserInput(categoryName);
                    goodsToShow = limitGoodsByPageNum(foundGoods, pageNum);
                }
            }
        }

        //Check sort type is set
        if (sortBy != null){
            SortType sortType = getSortType(sortBy);

            if (sortType != null)
                goodsToShow = sortGoodsByParam(goodsToShow, sortBy);
        }

        return goodsToShow;
    }

    @Override
    public Collection<Good> getGoodsByUserInput(String userInput) {
        if (userInput == null || userInput.length() == 0) return null;

        List<Good> foundGoods = new ArrayList<>();

        //Search by good name
        List<Good> allGoods = goodRepository.findAll();

        //Add goods with name match
        foundGoods.addAll(allGoods
                .stream()
                .filter(el -> el.getName().toLowerCase().contains(userInput.toLowerCase()))
                .collect(Collectors.toList()));

        //Add goods with description match
        foundGoods.addAll(allGoods
                .stream()
                .filter(el -> el.getDescription().toLowerCase().contains(userInput.toLowerCase()))
                .collect(Collectors.toList()));

        //Remove duplicates from found goods
        foundGoods = foundGoods
                .stream()
                .distinct()
                .collect(Collectors.toList());

        return foundGoods;
    }

    @Override
    public Good getGoodById(Long goodId) {
        Optional<Good> found = goodRepository.findById(goodId);
        return found.orElse(null);
    }

    @Override
    public void deleteGood(Good good) {
        if (good == null) return;
        goodRepository.delete(good);
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
    public Collection<Good> getRecommendedGoods() {
        return goodRepository.findAll().stream()
                .filter(el -> el.getRating() >= 4.5 && el.getOrdersCount() > 10)
                .sorted(Comparator.comparing(Good::getRating).thenComparing(Good::getOrdersCount).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Good> sortGoodsByParam(List<Good> goodsToSort, String sortTypeName) {
        SortType sortType = getSortType(sortTypeName);
        if (sortType == null) return goodsToSort;

        return switch (sortType){
            case POPULARITY -> (List<Good>) sortGoodsByOrdersCount(goodsToSort);
            case RATING -> (List<Good>) sortGoodsByRating(goodsToSort);
            case PRICE -> (List<Good>) sortGoodsByPrice(goodsToSort);
            case DISCOUNT -> (List<Good>) sortGoodsByDiscount(goodsToSort);
            case NAME -> (List<Good>) sortGoodsByName(goodsToSort);
        };
    }

    private boolean checkItemsExist(Object item){
        if (item == null) return false;

        if (item instanceof Collection){
            return ((Collection) item).size() != 0;
        }

        return false;
    }

    private List<Good> limitGoodsByPageNum(List<Good> goods, Integer pageNum){
//        if (pageNum == null || pageNum == 1 || 35*pageNum+36 >= goods.size())
//            return goods.stream().limit(35).collect(Collectors.toList());
//
//        return goods.subList(35 * pageNum + 1, 35 * pageNum + 36);

        if (pageNum == null || pageNum == 1)
            return goods.stream().limit(35).collect(Collectors.toList());

        pageNum-=1;

        int startFromIndex = 35*pageNum;
        int endIndex = startFromIndex + 35;

        //If last page has less than 35 elems select all to end
        if (endIndex >= goods.size())
            endIndex = goods.size()-1;

        if (startFromIndex >= goods.size())
            return goods.stream().limit(35).collect(Collectors.toList());

        //Check if only one page is necessary
        if (startFromIndex == endIndex) return goods;

        return goods.subList(startFromIndex, endIndex);
    }

    private Collection<Good> sortGoodsByDiscount(Collection<Good> goodsToSort) {
        if (!checkItemsExist(goodsToSort)) return null;

        List<Good> goodsWithDiscount = goodsToSort.stream()
                .filter(el -> el.getPriceBeforeDiscount() != null).sorted((el1, el2) ->
                        Integer.compare(el2.getPriceBeforeDiscount() - el2.getPrice(), el1.getPriceBeforeDiscount() - el1.getPrice())).collect(Collectors.toList());

        List<Good> goodsWithNoDiscount = goodsToSort.stream()
                .filter(el -> el.getPriceBeforeDiscount() == null)
                .collect(Collectors.toList());

        List<Good> resultGoods = new ArrayList<>();
        resultGoods.addAll(goodsWithDiscount);
        resultGoods.addAll(goodsWithNoDiscount);

        return resultGoods;
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
