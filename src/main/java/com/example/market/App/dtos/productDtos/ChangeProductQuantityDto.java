package com.example.market.App.dtos.productDtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ChangeProductQuantityDto {

    private AddCategoryDto category;

    private AddBrandDto brand;

    @NotNull(message = "model can not be null")
    private String model;

    @NotNull(message = "Price can not be null")
    @Min(value = 0, message = "Price can not be negative.")
    private Integer quantity;

    public ChangeProductQuantityDto() {
    }

    public ChangeProductQuantityDto(AddCategoryDto category, AddBrandDto brand, String model, Integer quantity) {
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
