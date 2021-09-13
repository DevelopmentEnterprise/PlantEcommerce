package com.WebMall.service.ordersServices;

import com.WebMall.model.*;
import com.WebMall.model.enums.DeliveryType;
import com.WebMall.model.enums.OrderStatus;
import com.WebMall.model.enums.PaymentType;
import com.WebMall.repository.CartItemRepository;
import com.WebMall.repository.OrderRepository;
import com.WebMall.repository.StoreRepository;
import com.WebMall.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public void createNewOrder(DeliveryType deliveryType, PaymentType paymentType, boolean cartRemove) {
        User loggedUser = userService.getLoggedUser();

        //Create new order
        Order order = new Order();
        order.setUser(loggedUser);
        order.setStatus(OrderStatus.NEW);
        order.setDeliveryType(deliveryType);
        order.setPaymentType(paymentType);
        order.setOrderDate(new Date());
        order.setOrderGoods(new ArrayList<>());
        order.setStores(new ArrayList<>());

        List<Store> stores = new ArrayList<>();
        List<CartItem> currentCart = new ArrayList<>(loggedUser.getCart());

        //Transform user cart to order items
        for (CartItem cartItem : loggedUser.getCart()){
            OrderGood orderGood = new OrderGood();
            orderGood.setName(cartItem.getGood().getName());
            orderGood.setDescription(cartItem.getGood().getDescription());
            orderGood.setOrder(order);
            orderGood.setPrice(cartItem.getGood().getPrice());
            orderGood.setQuantity(cartItem.getQuantity());

            order.getOrderGoods().add(orderGood);

            //Add order to all sellers
            stores.add(cartItem.getGood().getStore());

            //Increment quantity of sold goods for good seller
            cartItem.getGood().getStore().getStoreStats().setDiffCustomersCount(
                    cartItem.getGood().getStore().getStoreStats().getDiffCustomersCount() + 1);

            //Increase profit of seller store
            cartItem.getGood().getStore().getStoreStats().setProfit(
                    cartItem.getGood().getStore().getStoreStats().getProfit() + cartItem.getGood().getPrice() * cartItem.getQuantity());
        }

        //Loop through stores and add order to each of them
        for (Store store : stores){
            order.getStores().add(store);

            //Change unique customers store stats
            store.getStoreStats().setDiffCustomersCount(store.getStoreStats().getDiffCustomersCount() + 1);

            //Save store after changes made
            storeRepository.save(store);
        }

        //Save new order to database
        orderRepository.save(order);

        //Check if deleting user cart is required
        if (cartRemove){
            //Clear user cart
            loggedUser.getCart().clear();

            //Delete cart items records from database
            for (CartItem cartItem : currentCart){
                cartItemRepository.delete(cartItem);
            }

            userService.save(loggedUser);
        }
    }
}
