package com.example.market.App.domain.entities.People;

import com.example.market.App.domain.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "country")
public class Country extends BaseEntity {

    @Column(name = "country_name")
    private String countryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private Set<Towns> towns;

    public Country() {
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Set<Towns> getTowns() {
        return towns;
    }

    public void setTowns(Set<Towns> towns) {
        this.towns = towns;
    }
}
