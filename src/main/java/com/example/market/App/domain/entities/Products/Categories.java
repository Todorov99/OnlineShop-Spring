package com.example.market.App.domain.entities.Products;

import com.example.market.App.domain.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Categories extends BaseEntity {

    @Column(name = "category_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Products> products;

    public Categories() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Products> getProducts() {
        return products;
    }

    public void setProducts(Set<Products> products) {
        this.products = products;
    }

}
