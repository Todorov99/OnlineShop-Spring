package com.example.market.App.dtos.AddressDtos;

import javax.validation.constraints.Pattern;

public class AddTownDto {

    @Pattern(regexp = "([a-zA-Z]+|[a-zA-Z]+\\\\s[a-zA-Z]+){3,}", message = "Invalid town name.")
    private String townName;

    public AddTownDto() {
    }

    public AddTownDto(String townName) {
        this.townName = townName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
