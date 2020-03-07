package com.example.market.App.repositories;

import com.example.market.App.domain.entities.People.Towns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TownsRepository extends JpaRepository<Towns, Integer> {

    Optional<Towns> findByTownName(String townName);
}
