package com.luxoft.logeek.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SimpleEntity {
    @Id
    private Long id;

    private String name;

    @JoinColumn(name = "CHILD_ID")
    @ManyToOne(cascade = CascadeType.ALL)
    private ChildEntity childEntity;
}
