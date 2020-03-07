package com.example.market.App.domain.entities.Products;

import com.example.market.App.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "brands")
public class Brands extends BaseEntity {

    @Column(name = "brand", nullable = false)
    private String name;


    public Brands() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
