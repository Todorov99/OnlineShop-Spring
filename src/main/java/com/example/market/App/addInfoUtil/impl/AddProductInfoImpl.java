package com.example.market.App.addInfoUtil.impl;

import com.example.market.App.dtos.productDtos.*;
import com.example.market.App.addInfoUtil.AddProductInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class AddProductInfoImpl implements AddProductInfo {

    private final Scanner scanner;
    private final BufferedReader reader;
    private final ModelMapper modelMapper;

    @Autowired
    public AddProductInfoImpl(Scanner scanner, ModelMapper modelMapper, BufferedReader reader) {
        this.scanner = scanner;
        this.modelMapper = modelMapper;
        this.reader = reader;
    }

    @Override
    public AddProductDto addProductInfo() throws IOException {

        AddCategoryDto addCategoryDto = addCategoryInfo();

        AddBrandDto addBrandDto = addBrandInfo();

        System.out.println("Enter model: ");
        String model = this.reader.readLine();

        System.out.println("Enter minimal price: ");
        BigDecimal minimalPrice = this.scanner.nextBigDecimal();

        System.out.println("Enter price: ");
        BigDecimal price = this.scanner.nextBigDecimal();

        System.out.println("Enter quantity: ");
        Integer quantity = this.scanner.nextInt();

        System.out.println("Enter discount: ");
        double discount = this.scanner.nextDouble();

        return new AddProductDto(addCategoryDto, addBrandDto, model, minimalPrice, price, quantity, discount);
    }

    @Override
    public DeleteProductDto deleteProductInfo() throws IOException {

        AddCategoryDto category = addCategoryInfo();

        AddBrandDto brand = addBrandInfo();

        System.out.println("Enter model: ");
        String model = this.reader.readLine();

        return new DeleteProductDto(category, brand, model);
    }

    @Override
    public ChangeProductPricesDto changeProductPriceInfo() throws IOException {

        AddCategoryDto category = addCategoryInfo();

        AddBrandDto brand = addBrandInfo();

        System.out.println("Enter model: ");
        String model = this.reader.readLine();

        System.out.println("Enter price: ");
        BigDecimal price = this.scanner.nextBigDecimal();

        return new ChangeProductPricesDto(category, brand, model, price);
    }

    @Override
    public ChangeProductDiscountDto changeProductDiscountInfo() throws IOException {

        AddCategoryDto category = addCategoryInfo();

        AddBrandDto brand = addBrandInfo();

        System.out.println("Enter model: ");
        String model = this.reader.readLine();

        System.out.println("Enter discount");
        double discount = this.scanner.nextDouble();

        return new ChangeProductDiscountDto(category, brand,model, discount);
    }

    @Override
    public ChangeProductQuantityDto changeProductQuantityInfo() throws IOException {

        AddCategoryDto category = addCategoryInfo();

        AddBrandDto brand = addBrandInfo();

        System.out.println("Enter model: ");
        String model = this.reader.readLine();

        System.out.println("Enter quantity: ");
        Integer quantity = this.scanner.nextInt();

        return new ChangeProductQuantityDto(category, brand, model, quantity);
    }

    @Override
    public BuyProductDto buyProductInfo() throws IOException {
        return this.modelMapper.map(changeProductQuantityInfo(), BuyProductDto.class);
    }

    private AddCategoryDto addCategoryInfo(){
        System.out.println("Enter category: ");
        return new AddCategoryDto(this.scanner.next());
    }

    private AddBrandDto addBrandInfo(){
        System.out.println("Enter brand: ");
        return new AddBrandDto(this.scanner.next());
    }
}
