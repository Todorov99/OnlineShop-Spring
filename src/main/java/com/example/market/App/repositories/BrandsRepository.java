package com.example.market.App.repositories;

import com.example.market.App.domain.entities.Products.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface BrandsRepository extends JpaRepository<Brands, Integer> {

    Optional<Brands> findByName(String name);
}
