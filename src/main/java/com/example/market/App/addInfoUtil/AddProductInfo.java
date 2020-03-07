package com.example.market.App.addInfoUtil;

import com.example.market.App.dtos.productDtos.*;
import org.hibernate.sql.Delete;

import java.io.IOException;

public interface AddProductInfo {

    AddProductDto addProductInfo() throws IOException;

    DeleteProductDto deleteProductInfo() throws IOException;

    ChangeProductPricesDto changeProductPriceInfo() throws IOException;

    ChangeProductDiscountDto changeProductDiscountInfo() throws IOException;

    ChangeProductQuantityDto changeProductQuantityInfo() throws IOException;

    BuyProductDto buyProductInfo() throws IOException;
}
