package com.example.market.App.repositories;

import com.example.market.App.domain.entities.Products.Brands;
import com.example.market.App.domain.entities.Products.Categories;
import com.example.market.App.domain.entities.Products.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {

    Optional<Products> findByCategoryNameAndBrandNameAndModel(String category_name, String brand_name, String model);

    @Modifying
    @Query("delete from Products as p where p.id = :productID ")
    void deleteProductById(@Param(value = "productID") Integer id);

    @Query("select p from Products as p join p.category as pc order by pc.name asc")
    List<Products> allProducts();

    @Modifying
    @Query("update Products as p set p.price = :price, p.promotionalPrice = :promotionalPrice where p.id = :id")
    void updateProductsPrice(@Param(value = "price")BigDecimal price,
                             @Param(value = "promotionalPrice") BigDecimal promotionalPrice, @Param(value = "id") Integer id);

    @Modifying
    @Query("update Products as p set p.discount = :discount, p.promotionalPrice = :price, p.minimalPrice = :minimalPrice where p.id = :id")
    void updateProductDiscountPriceAndMinimalPrice(@Param(value = "discount") double discount,
                                                   @Param(value = "price") BigDecimal price, @Param(value = "minimalPrice") BigDecimal minimalPrice,
                                                   @Param(value = "id") Integer id);

    @Modifying
    @Query("update Products as p set p.minimalPrice = :price where p.id = :id")
    void updateProductMinimalPrice(@Param(value = "price") BigDecimal price, @Param(value = "id") Integer id);

    @Modifying
    @Query("update Products as p set p.discount = :discount, p.promotionalPrice = p.price - (p.price * (p.discount / 100)) where p.id = :id")
    void updateProductDiscount(@Param(value = "discount") double discount, @Param(value = "id") Integer id);

    @Modifying
    @Query("update Products as p set p.quantity = :quantity where p.id = :id")
    void updateProductQuantity(@Param(value = "quantity") Integer quantity, @Param(value = "id") Integer id);

    @Modifying
    @Query("update Products as p set p.quantity = p.quantity - :quantity where p.id = :id")
    void  buyProduct(@Param(value = "quantity") Integer quantity, @Param(value = "id") Integer id);

    @Query("select p from Products as p where p.quantity = 0 ")
    List<Products> findAllOutOfStocksProducts();
}
