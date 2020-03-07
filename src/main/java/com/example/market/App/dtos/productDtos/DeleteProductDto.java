package com.example.market.App.dtos.productDtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DeleteProductDto implements Serializable {

    @Expose
    private AddCategoryDto category;

    @Expose
    private AddBrandDto brand;

    @Expose
    private String model;

    public DeleteProductDto() {
    }

    public DeleteProductDto(AddCategoryDto category, AddBrandDto brand, String model) {
        this.category = category;
        this.brand = brand;
        this.model = model;
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
}
