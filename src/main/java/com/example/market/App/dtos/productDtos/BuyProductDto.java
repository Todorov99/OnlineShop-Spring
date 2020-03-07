package com.example.market.App.dtos.productDtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class BuyProductDto implements Serializable {

    @Expose
    private AddCategoryDto category;

    @Expose
    private AddBrandDto brandDto;

    @Expose
    private String model;

    @Expose
    @NotNull(message = "Price can not be null")
    @Min(value = 0, message = "Quantity can not be negative.")
    private Integer quantity;

    public BuyProductDto() {
    }

    public BuyProductDto(AddCategoryDto category, AddBrandDto brandDto, String model, Integer quantity) {
        this.category = category;
        this.brandDto = brandDto;
        this.model = model;
        this.quantity = quantity;
    }

    public AddCategoryDto getCategory() {
        return category;
    }

    public void setCategory(AddCategoryDto category) {
        this.category = category;
    }

    public AddBrandDto getBrandDto() {
        return brandDto;
    }

    public void setBrandDto(AddBrandDto brandDto) {
        this.brandDto = brandDto;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyProductDto that = (BuyProductDto) o;
        return Objects.equals(category, that.category) &&
                Objects.equals(brandDto, that.brandDto) &&
                Objects.equals(model, that.model) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, brandDto, model, quantity);
    }

    @Override
    public String toString() {
        return "BuyProductDto{" +
                "category=" + category.getName() +
                ", brandDto=" + brandDto.getName() +
                ", model='" + model + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
