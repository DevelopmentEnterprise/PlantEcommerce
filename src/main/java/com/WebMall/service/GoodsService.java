package com.WebMall.service;

import com.WebMall.model.Good;

import java.util.Collection;

public interface GoodsService {

    Good getGoodById(Long goodId);

    /**
     * @return goods that have discount value not null
     */
    //Get goods by parameters
    Collection<Good> getGoodsWithDiscount();

    /**
     * @return goods with rating >= 4.5
     */
    Collection<Good> getHitGoods();

    boolean addNewGood(Good good);
    boolean removeGoodById(Long goodId);
    boolean editGood(Good goodToEdit);
//    Collection<Good> getGoodByCategoryName(String categoryName);
//    Collection<Good> getGoodsByCategoryId(Long categoryId);
//    Collection<Good> getGoodsByStoreId(Long storeId);

    //Sort goods by parameters
    Collection<Good> sortGoodsByDiscount(Collection<Good> goodsToSort);
    Collection<Good> sortGoodsByName(Collection<Good> goodsToSort);
    Collection<Good> sortGoodsByOrdersCount(Collection<Good> goodsToSort);
    Collection<Good> sortGoodsByWishesCount(Collection<Good> goodsToSort);
    Collection<Good> sortGoodsByCartCount(Collection<Good> goodsToSort);
}
