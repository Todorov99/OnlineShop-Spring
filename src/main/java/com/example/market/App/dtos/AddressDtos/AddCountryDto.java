package com.example.market.App.dtos.AddressDtos;

public class AddCountryDto {

    private String countryName;

    public AddCountryDto() {
    }

    public AddCountryDto(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
