package com.luxoft.logeek.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ChildEntity {

    @Id
    private Long id;

    @JoinColumn(name = "SIMPLE_ENTITY_ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SimpleEntity simpleEntity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private RatingEntity ratingEntity;

    public ChildEntity(Long id) {
        this.id = id;
    }

    public ChildEntity() {
    }
}
