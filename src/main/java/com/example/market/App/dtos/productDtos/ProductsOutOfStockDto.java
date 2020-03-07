package com.example.market.App.dtos.productDtos;

public class ProductsOutOfStockDto {

   private AddCategoryDto category;

   private AddBrandDto brand;

   private String model;

   private Integer quantity;

    public ProductsOutOfStockDto() {
    }

    public ProductsOutOfStockDto(AddCategoryDto category, AddBrandDto brand, String model, Integer quantity) {
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

        sb.append(String.format("%15s %5s %10s %5s %20s %8s %4s", this.category.getName(), "|", this.brand.getName(), "|",  this.model,  "|", this.quantity));
        return sb.toString();
    }
}
