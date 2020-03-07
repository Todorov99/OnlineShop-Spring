package com.example.market.App.services;

import com.example.market.App.dtos.productDtos.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ProductService{

    String addProduct(AddProductDto addProductDto);

    String deleteProduct(DeleteProductDto deleteProductDto) throws IOException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;

    List<Object> seeAllProduct();

    String changeProductPrice(ChangeProductPricesDto changeProductPricesDto) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;

    String changeProductMinimalPrice(ChangeProductPricesDto dto) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    String changeProductDiscount(ChangeProductDiscountDto changeProductDiscountDto);

    String changeProductQuantity(ChangeProductQuantityDto changeProductQuantityDto);

    String buyProduct(BuyProductDto buyProductDto);

    String finalizeOrder() throws IOException;

    List<ExportAllProductsToClientDto> seeShoppingBag();

    List<ProductsOutOfStockDto> outOfStockProducts();

}
