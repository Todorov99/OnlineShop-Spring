package com.example.market.App.dtos.PersonDto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class RetireEmployeeDto {

    private String firstName;

    private String lastName;

    private String email;

    public RetireEmployeeDto() {
    }

    public RetireEmployeeDto(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
