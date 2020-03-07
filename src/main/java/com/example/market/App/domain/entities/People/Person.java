package com.example.market.App.domain.entities.People;

import com.example.market.App.domain.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "people")
public class Person extends BaseEntity {

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PersonTypes types;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_name_id")
    private PersonNames names;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Orders> orders;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Towns town;

    public Person() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonTypes getTypes() {
        return types;
    }

    public void setTypes(PersonTypes types) {
        this.types = types;
    }

    public PersonNames getNames() {
        return names;
    }

    public void setNames(PersonNames names) {
        this.names = names;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Towns getTown() {
        return town;
    }

    public void setTown(Towns town) {
        this.town = town;
    }
}
