package com.WebMall.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "storestats")
public class StoreStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer profit;

    private Integer goodsSold;

    @OneToOne(mappedBy = "storeStats")
    private Store store;

    public Long getId() {
        return id;
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

    public Integer getGoodsSold() {
        return goodsSold;
    }

    public void setGoodsSold(Integer goodsSold) {
        this.goodsSold = goodsSold;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
