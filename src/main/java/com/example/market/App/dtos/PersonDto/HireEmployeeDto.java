package com.example.market.App.dtos.PersonDto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class HireEmployeeDto {

    private PersonNamesDto names;

    @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]+\\.[a-z]{2,4}", message = "Invalid email")
    @NotNull(message = "Email can not be empty.")
    private String email;

    @Pattern(regexp = "^[0-9]{4}$", message = "Incorrect shift pass")
    @NotNull(message = "You have to enter your shift pass")
    private String password;

    public HireEmployeeDto() {
    }

    public HireEmployeeDto(PersonNamesDto names, String email, String password) {
        this.names = names;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
