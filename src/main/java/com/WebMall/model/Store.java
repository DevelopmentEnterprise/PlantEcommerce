package com.WebMall.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private String name;

    private Float rating;

    private String description;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "store")
    private List<Order> orders;

    @OneToOne
    @JoinColumn(name = "storestats_id")
    private StoreStats storeStats;

    @OneToMany(mappedBy = "store")
    private List<StoreAddress> storeAddresses;

    @OneToMany(mappedBy = "store")
    private List<Good> goods;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public StoreStats getStoreStats() {
        return storeStats;
    }

    public void setStoreStats(StoreStats storeStats) {
        this.storeStats = storeStats;
    }

    public List<StoreAddress> getStoreAddresses() {
        return storeAddresses;
    }

    public void setStoreAddresses(List<StoreAddress> storeAddresses) {
        this.storeAddresses = storeAddresses;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }
}
