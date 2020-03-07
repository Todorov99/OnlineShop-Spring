package com.example.market.App.dtos.productDtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;

public class AddProductDto implements Serializable {

    private AddCategoryDto category;

    private AddBrandDto brand;

    @NotNull
    private String model;

    @NotNull
    @Min(value = 0, message = "Minimal price can not be negative.")
    private BigDecimal minimalPrice;

    @NotNull
    @Min(value = 0, message = "Price can not be negative.")
    private BigDecimal price;

    @NotNull
    @Min(value = 0, message = "Quantity can not be negative.")
    private Integer quantity;

    @NotNull
    @Min(value = 0, message = "Minimal price can not be negative.")
    @Max(value = 99, message = "You can no enter more than 99% discount")
    private double discount;

    @NotNull
    @Min(value = 0, message = "Promotional price can not be negative.")
    private BigDecimal promotionalPrice;

    public AddProductDto() {
    }

    public AddProductDto(AddCategoryDto category, AddBrandDto brand, String model, BigDecimal minimalPrice, BigDecimal price, Integer quantity, double discount) {
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.minimalPrice = minimalPrice;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.promotionalPrice = this.price.subtract(this.price.multiply(new BigDecimal(this.discount / 100)));
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

    public BigDecimal getMinimalPrice() {
        return minimalPrice;
    }

    public void setMinimalPrice(BigDecimal minimalPrice) {
        this.minimalPrice = minimalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public BigDecimal getPromotionalPrice() {
        return promotionalPrice;
    }

    public void setPromotionalPrice(BigDecimal promotionalPrice) {
        this.promotionalPrice = promotionalPrice;
    }
}
