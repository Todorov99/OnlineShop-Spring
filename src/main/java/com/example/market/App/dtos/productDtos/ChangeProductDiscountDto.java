package com.example.market.App.dtos.productDtos;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ChangeProductDiscountDto {

    private AddCategoryDto category;

    private AddBrandDto brand;

    @NotNull(message = "model can not be null")
    private String model;


    @NotNull(message = "Price can not be null")
    @Min(value = 0, message = "Price can not be negative.")
    private double discount;

    public ChangeProductDiscountDto() {
    }

    public ChangeProductDiscountDto(AddCategoryDto category, AddBrandDto brand, String model, double discount) {
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.discount = discount;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
