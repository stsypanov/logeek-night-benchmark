package com.luxoft.logeek.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Entity
public class ChildEntity {
    @Id
    private Long id;
    @ManyToOne
    private RatingEntity rating;

}
