package com.WebMall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "goods")
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80)
    @NotNull
    private String name;

    @Column(length = 200)
    @NotNull
    private String description;

    private Integer priceBeforeDiscount;

    @NotNull
    private Boolean isOnSale;

    private Integer ordersCount;

    private Integer wishesCount;

    private Integer cartCount;

    private Float rating;

    private Integer price;

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private Store store;

    @OneToMany(mappedBy = "good", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<GoodOption> goodOptions;

    @OneToMany(mappedBy = "good")
    @JsonIgnore
    private List<Review> reviews;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<GoodCategory> goodCategories;

    @OneToMany(mappedBy = "good", cascade = CascadeType.ALL)
    private List<GoodImage> goodImages;

    @JsonIgnore
    @OneToOne(mappedBy = "good")
    private CartItem cartItem;

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public void setPriceBeforeDiscount(Integer priceBeforeDiscount) {
        this.priceBeforeDiscount = priceBeforeDiscount;
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public List<GoodImage> getGoodImages() {
        return goodImages;
    }

    public void setGoodImages(List<GoodImage> goodImages) {
        this.goodImages = goodImages;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }
}