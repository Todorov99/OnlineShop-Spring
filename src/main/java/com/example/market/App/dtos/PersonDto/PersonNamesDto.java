package com.example.market.App.dtos.PersonDto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PersonNamesDto implements Serializable {

    @Expose
    private String firstName;

    @Expose
    @NotNull(message = "Last name can not be null.")
    @Size(min = 3)
    private String lastName;

    public PersonNamesDto() {
    }

    public PersonNamesDto(String firstName, String lastName) {
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
