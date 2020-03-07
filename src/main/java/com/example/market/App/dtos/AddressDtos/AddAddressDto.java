package com.example.market.App.dtos.AddressDtos;

import javax.validation.constraints.Pattern;

public class AddAddressDto {

    private String address;

    public AddAddressDto() {
    }

    public AddAddressDto(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
