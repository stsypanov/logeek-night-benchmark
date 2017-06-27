package com.luxoft.logeek.entity;

import javax.persistence.*;

@Entity
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private boolean hasGoodRating;

    public boolean hasGoodRating() {
        return hasGoodRating;
    }
}
