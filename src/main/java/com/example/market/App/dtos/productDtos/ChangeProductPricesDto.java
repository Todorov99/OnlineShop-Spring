package com.example.market.App.dtos.productDtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class ChangeProductPricesDto {

    @Expose
    @NotNull
    private AddCategoryDto category;

    @Expose
    @NotNull
    private AddBrandDto brand;

    @Expose
    @NotNull(message = "model can not be null")
    private String model;

    @Expose
    @NotNull(message = "Price can not be null")
    @Min(value = 0, message = "Price can not be negative.")
    private BigDecimal price;

    public ChangeProductPricesDto() {
    }

    public ChangeProductPricesDto(AddCategoryDto category, AddBrandDto brand, String model, BigDecimal price) {
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public AddCategoryDto getCategory() {
        return category;
    }

    public void setCategory(AddCategoryDto category) {
        this.category = category;
    }

    public AddBrandDto getBrand() {
        return brand;
    }

    public void setBrand(AddBrandDto brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
