package com.WebMall.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "goodoptions")
public class GoodOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    @NotNull
    private String name;

    @NotNull
    private Integer price;

//    @OneToOne
//    private GoodImage img;

    @ManyToOne
    @JoinColumn(name = "good_id")
    private Good good;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }
}
