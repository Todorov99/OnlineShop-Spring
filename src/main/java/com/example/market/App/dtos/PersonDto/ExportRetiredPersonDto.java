package com.example.market.App.dtos.PersonDto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class ExportRetiredPersonDto implements Serializable {

    @Expose
    private String email;

    @Expose
    private PersonNamesDto names;

    public ExportRetiredPersonDto() {
    }

    public ExportRetiredPersonDto(String email, PersonNamesDto names) {
        this.email = email;
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonNamesDto getNames() {
        return names;
    }

    public void setNames(PersonNamesDto names) {
        this.names = names;
    }
}
