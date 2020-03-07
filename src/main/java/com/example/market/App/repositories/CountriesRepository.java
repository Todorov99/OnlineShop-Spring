package com.example.market.App.repositories;

import com.example.market.App.domain.entities.People.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountriesRepository extends JpaRepository<Country, Integer> {

    Optional<Country> findByCountryName(String countryName);

}
