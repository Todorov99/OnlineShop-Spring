package com.example.market.App.dtos.productDtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddCategoryDto {

    @Expose
    @NotNull(message = "Category name can not be null.")
    @Pattern(regexp = "^[A-Z][a-z]{3,}+$", message = "Category name should start with uppercase letter and minimum length should be 3.")
    private String name;

    public AddCategoryDto(String name) {
        this.name = name;
    }

    public AddCategoryDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
