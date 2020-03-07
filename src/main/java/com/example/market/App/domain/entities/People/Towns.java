package com.example.market.App.domain.entities.People;

import com.example.market.App.domain.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Towns extends BaseEntity {

    @Column(name = "town_name")
    private String townName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "town")
    private Set<Person> people;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "towns")
    private Set<Address> addresses;
    public Towns() {
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
