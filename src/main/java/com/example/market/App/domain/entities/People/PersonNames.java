package com.example.market.App.domain.entities.People;

import com.example.market.App.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "names")
public class PersonNames extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;


    public PersonNames() {
    }

    public PersonNames(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
