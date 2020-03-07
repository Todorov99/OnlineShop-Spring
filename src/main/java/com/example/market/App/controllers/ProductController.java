package com.example.market.App.controllers;

import com.example.market.App.services.ProductService;
import com.example.market.App.addInfoUtil.impl.AddProductInfoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Component
public class ProductController {

    private final ProductService productService;
    private final AddProductInfoImpl addProductInfo;

    @Autowired
    public ProductController(ProductService productService, AddProductInfoImpl addProductInfo) {
        this.productService = productService;
        this.addProductInfo = addProductInfo;
    }

    public void addProduct() throws IOException {
        System.out.println(this.productService.addProduct(this.addProductInfo.addProductInfo()));
    }

    public void seeAllProducts() {
        this.productService.seeAllProduct().forEach(System.out::println);
    }

    public void changeProductPrice() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
        System.out.println(this.productService.changeProductPrice(this.addProductInfo.changeProductPriceInfo()));
    }

    public void changeProductMinimalPrice() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
        System.out.println(this.productService.changeProductMinimalPrice(this.addProductInfo.changeProductPriceInfo()));
    }

    public void changeProductDiscount() throws IOException {
        System.out.println(this.productService.changeProductDiscount(this.addProductInfo.changeProductDiscountInfo()));
    }

    public void deleteProduct() throws IOException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        System.out.println(this.productService.deleteProduct(this.addProductInfo.deleteProductInfo()));
    }

    public void changeProductQuantity() throws IOException {
        System.out.println(this.productService.changeProductQuantity(this.addProductInfo.changeProductQuantityInfo()));
    }

    public void buyProduct() throws IOException {
        System.out.println(this.productService.buyProduct(this.addProductInfo.buyProductInfo()));
    }

    public void seeShoppingBag() {
        this.productService.seeShoppingBag().forEach(System.out::println);
    }

    public void finalizeOrder() throws IOException {
        System.out.println(this.productService.finalizeOrder());
    }

    public void outOfStockProducts(){
        this.productService.outOfStockProducts().forEach(System.out::println);
    }
}
