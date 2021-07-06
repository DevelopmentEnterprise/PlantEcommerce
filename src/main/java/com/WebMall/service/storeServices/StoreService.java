package com.WebMall.service.storeServices;

import com.WebMall.model.Good;
import com.WebMall.model.GoodCategory;
import com.WebMall.model.Order;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StoreService {
    List<Good> filterStoreGoods(List<Good> goodsToFilter, String filterParam);
    List<Order> filterStoreOrders(List<Order> ordersToFilter, String filterParam);
    void uploadGoodImages(List<MultipartFile> images);
    void createNewGood(Good createdGood, List<MultipartFile> images, GoodCategory goodCategory);
    void saveGood(Good editedGood, Long goodId, List<MultipartFile> images, GoodCategory goodCategory);
}
