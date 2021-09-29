package com.WebMall.service.goodsServices;

import com.WebMall.model.Good;
import com.WebMall.model.enums.SortType;

import java.util.Collection;
import java.util.List;

public interface GoodsService {

    /**
     * @return goods that have discount value not null
     */
    Collection<Good> getGoodsWithDiscount();

    /**
     * @return goods with rating >= 4.5
     */
    Collection<Good> getHitGoods();
    Collection<Good> getRecommendedGoods();
    Collection<Good> getGoodsByUserInput(String categoryName);

    Good getGoodById(Long goodId);
    void deleteGood(Good good);
    List<Good> getGoodsByRequestParams(String categoryName, Integer pageNum, String sortBy);
    int getPagersCountRequired(int allGoodsCount);
    SortType getSortType(String sortType);
    List<Good> sortGoodsByParam(List<Good> goodsToSort, String sortTypeName);
}
