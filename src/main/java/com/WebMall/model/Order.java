package com.WebMall.model;

import com.WebMall.model.enums.DeliveryType;
import com.WebMall.model.enums.OrderType;
import com.WebMall.model.enums.Status;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NonNull
//    private OrderType orderType;

    @NonNull
    private Status status;

    private Date orderDate;

    private Integer sum;

    @NonNull
    private DeliveryType deliveryType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderGood> orderGoods;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Long getId() {
        return id;
    }

    @NonNull
    public Status getStatus() {
        return status;
    }

    public void setStatus(@NonNull Status status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @NonNull
    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(@NonNull DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderGood> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGood> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
