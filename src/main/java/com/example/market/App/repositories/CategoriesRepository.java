package com.example.market.App.repositories;

import com.example.market.App.domain.entities.Products.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

    Optional<Categories> findByName(String category);
}
