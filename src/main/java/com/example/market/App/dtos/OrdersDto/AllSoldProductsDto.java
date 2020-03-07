package com.example.market.App.dtos.OrdersDto;

import com.example.market.App.dtos.productDtos.AddBrandDto;
import com.example.market.App.dtos.productDtos.AddCategoryDto;
import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AllSoldProductsDto implements Serializable {

    @Expose
    @NotNull(message = "The product should have category.")
    private AddCategoryDto category;

    @Expose
    @NotNull(message = "The product should have brand.")
    private AddBrandDto brand;

    @Expose
    @NotNull(message = "The product should have model.")
    private String model;

    @Expose
    @NotNull(message = "The product should have quantity.")
    private Integer quantity;


    public AllSoldProductsDto() {
    }

    public AllSoldProductsDto(AddCategoryDto category, AddBrandDto brand, String model, Integer quantity) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%15s %5s %15s %10s %25s %20s %13s", this.category.getName(), "|", this.brand.getName(), "|", this.model, "|", this.quantity));
        return sb.toString();
    }
}
