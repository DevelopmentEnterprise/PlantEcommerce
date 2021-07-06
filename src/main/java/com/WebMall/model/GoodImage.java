package com.WebMall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "goodimages")
public class GoodImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageSrc;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "good_id")
    @JsonIgnore
    private Good good;

//    @OneToOne
//    @JsonIgnore
//    @JoinColumn(name = "good-option_id")
//    private GoodOption goodOption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }
}
