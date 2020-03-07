package com.example.market.App.dtos.productDtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class ExportAllAvailableProductsToEmployeesDto implements Serializable {

    @Expose
    private AddCategoryDto category;

    @Expose
    private AddBrandDto brand;

    @Expose
    private String model;

    @Expose
    private BigDecimal minimalPrice;

    @Expose
    private BigDecimal price;

    @Expose
    private Integer quantity;

    @Expose
    private double discount;

    @Expose
    private BigDecimal promotionalPrice;

    public ExportAllAvailableProductsToEmployeesDto() {
    }

    public ExportAllAvailableProductsToEmployeesDto(AddCategoryDto category, AddBrandDto brand, String model,
                                                    BigDecimal minimalPrice, BigDecimal price,
                                                    Integer quantity, double discount, BigDecimal promotionalPrice) {
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.minimalPrice = minimalPrice;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.promotionalPrice = promotionalPrice;
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


    @Override
    public String toString() {
            StringBuilder sb = new StringBuilder();

        sb.append(String.format("%15s %5s %10s %5s %20s %20s %12.2f %1s %5s %12.2f %1s %5s %8s %1s %8s %13.2f %1s", this.category.getName(), "|", this.brand.getName(), "|",  this.model, "|",  this.price, "lv.",  "|", this.minimalPrice, "lv.", "|", this.discount, "%", "|", this.promotionalPrice, "lv."));
            return sb.toString();
    }


}
