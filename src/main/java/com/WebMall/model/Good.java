package com.WebMall.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "goods")
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80)
    private String name;

    @Column(length = 200)
    private String description;

    private Byte discount;

    private Boolean isOnSale;

    private Integer ordersCount;

    private Integer wishesCount;

    private Integer cartCount;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "good")
    private List<GoodOption> goodOptions;

    @OneToMany(mappedBy = "good")
    private List<Review> reviews;

    @ManyToMany
    private List<GoodCategory> goodCategories;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getDiscount() {
        return discount;
    }

    public void setDiscount(Byte discount) {
        this.discount = discount;
    }

    public Boolean getOnSale() {
        return isOnSale;
    }

    public void setOnSale(Boolean onSale) {
        isOnSale = onSale;
    }

    public Integer getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(Integer ordersCount) {
        this.ordersCount = ordersCount;
    }

    public Integer getWishesCount() {
        return wishesCount;
    }

    public void setWishesCount(Integer wishesCount) {
        this.wishesCount = wishesCount;
    }

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<GoodOption> getGoodOptions() {
        return goodOptions;
    }

    public void setGoodOptions(List<GoodOption> goodOptions) {
        this.goodOptions = goodOptions;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<GoodCategory> getGoodCategories() {
        return goodCategories;
    }

    public void setGoodCategories(List<GoodCategory> goodCategories) {
        this.goodCategories = goodCategories;
    }
}
