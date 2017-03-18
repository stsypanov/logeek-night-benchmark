package com.luxoft.logeek.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RatingEntity {
    @Id
    private Long id;
    @Column
    private boolean hasGoodRating;

    public boolean hasGoodRating() {
        return hasGoodRating;
    }
}
