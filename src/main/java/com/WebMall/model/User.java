package com.WebMall.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private String firstName;

    @Column(length = 45)
    private String lastName;

    @Column(length = 50)
    @NotNull
    private String email;

    @Column(length = 20)
    private String tel;

    @Column(length = 15)
    private String country;

    @Column(length = 20)
    private String city;

    @NotNull
    private String password;

    @Transient
    private String passwordConfirm;

    private Integer bonusesCount;

    private Integer ordersCount;

    private Integer goodsBoughtCount;

    @ManyToMany
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @ManyToMany
    private List<Coupon> coupons;

    @OneToOne(mappedBy = "user")
    private Store store;

    @OneToMany(mappedBy = "user")
    private List<Review> userReviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Integer getBonusesCount() {
        return bonusesCount;
    }

    public void setBonusesCount(Integer bonusesCount) {
        this.bonusesCount = bonusesCount;
    }

    public Integer getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(Integer ordersCount) {
        this.ordersCount = ordersCount;
    }

    public Integer getGoodsBoughtCount() {
        return goodsBoughtCount;
    }

    public void setGoodsBoughtCount(Integer goodsBoughtCount) {
        this.goodsBoughtCount = goodsBoughtCount;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public List<Review> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<Review> userReviews) {
        this.userReviews = userReviews;
    }
}
