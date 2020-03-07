package com.example.market.App.dtos.PersonDto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class ExportAllEmployeesDto implements Serializable {

    @Expose
    private PersonNamesDto names;
    @Expose
    private String email;

    public ExportAllEmployeesDto() {
    }

    public ExportAllEmployeesDto(PersonNamesDto names, String email) {
        this.names = names;
        this.email = email;
    }


    public PersonNamesDto getNames() {
        return names;
    }

    public void setNames(PersonNamesDto names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%10s %5s %15s %5s %10s", this.names.getFirstName(), "|",  this.names.getLastName(), "|", this.email));        return sb.toString();
    }

}
