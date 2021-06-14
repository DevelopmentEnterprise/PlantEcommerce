package com.WebMall.model;

import javax.persistence.*;

@Entity
@Table(name = "storeaddresses")
public class StoreAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String city;

    @Column(length = 45)
    private String street;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
