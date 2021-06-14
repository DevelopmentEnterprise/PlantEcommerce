package com.WebMall.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250)
    private String text;

    private Byte mark;

    @ManyToOne
    @JoinColumn(name = "good_id")
    private Good good;

    @OneToMany(mappedBy = "review")
    private List<ReviewImage> reviewImages;

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Byte getMark() {
        return mark;
    }

    public void setMark(Byte mark) {
        this.mark = mark;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public List<ReviewImage> getReviewImages() {
        return reviewImages;
    }

    public void setReviewImages(List<ReviewImage> reviewImages) {
        this.reviewImages = reviewImages;
    }
}
