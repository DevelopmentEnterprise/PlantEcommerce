package com.WebMall.service.ordersServices;

import com.WebMall.model.enums.DeliveryType;
import com.WebMall.model.enums.PaymentType;

public interface OrderService {
    void createNewOrder(DeliveryType deliveryType, PaymentType paymentType, boolean cartRemove);
}
