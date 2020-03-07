package com.example.market.App.repositories;

import com.example.market.App.domain.entities.People.PersonNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonNamesRepository extends JpaRepository<PersonNames, Integer> {

}
