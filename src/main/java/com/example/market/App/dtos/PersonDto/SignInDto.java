package com.example.market.App.dtos.PersonDto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SignInDto {

    // # Length >=3
    // # Valid characters: a-z, A-Z, 0-9, points, dashes and underscores.
    @Expose
    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,}$", message = "Invalid username.")
    @NotNull(message = "Username can not be empty.")
    private String username;

    // # a digit must occur at least once
    // # a lower case letter must occur at least once
    // # an upper case letter must occur at least once
    // # a special character must occur at least once
    // # no whitespace allowed in the entire string
    // # anything, at least eight places though
    // # end-of-string
    @Expose
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$", message = "Invalid password")
    @NotNull(message = "Password can not be empty.")
    private String password;

    @Expose
    @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]+\\.[a-z]{2,4}", message = "Invalid email")
    @NotNull(message = "Email can not be empty.")
    private String email;

    private PersonNamesDto personNamesDto;

    public SignInDto() {
    }

    public SignInDto(String username, String password, String email, PersonNamesDto personNamesDto) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.personNamesDto = personNamesDto;
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

    public PersonNamesDto getPersonNamesDto() {
        return personNamesDto;
    }

    public void setPersonNamesDto(PersonNamesDto personNamesDto) {
        this.personNamesDto = personNamesDto;
    }
}
