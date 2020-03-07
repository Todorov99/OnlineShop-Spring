package com.example.market.App.dtos.AddressDtos;

public class SetLivingTownDto {


    private AddCountryDto country;

    private AddTownDto town;

    private AddAddressDto address;

    public SetLivingTownDto() {
    }

    public SetLivingTownDto(AddCountryDto country, AddTownDto town, AddAddressDto address) {
        this.country = country;
        this.town = town;
        this.address = address;
    }

    public AddCountryDto getCountry() {
        return country;
    }

    public void setCountry(AddCountryDto country) {
        this.country = country;
    }

    public AddTownDto getTown() {
        return town;
    }

    public void setTown(AddTownDto town) {
        this.town = town;
    }

    public AddAddressDto getAddress() {
        return address;
    }

    public void setAddress(AddAddressDto address) {
        this.address = address;
    }
}
