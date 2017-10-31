package com.luxoft.logeek.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
public class SimpleEntity {
    @Id
    @Column(name = "SIMPLE_ENTITY_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "simpleEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ChildEntity> children = new ArrayList<>();

    public SimpleEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    protected SimpleEntity() {
    }

    public void addChild(ChildEntity child) {
        child.setSimpleEntity(this);
        children.add(child);
    }
}
