package com.example.market.App.dtos.productDtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddBrandDto {

    @Expose
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Brand name should start with uppercase letter.")
    private String name;

    public AddBrandDto(String name) {
        this.name = name;
    }

    public AddBrandDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
