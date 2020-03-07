package com.example.market.App.repositories;

import com.example.market.App.domain.entities.People.Person;
import com.example.market.App.domain.entities.People.PersonTypes;
import com.example.market.App.domain.entities.People.Towns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByUsername(String username);

    Optional<Person> findByEmail(String email);

    List<Person> findByTypes(PersonTypes types);

    void deleteById(Integer id);

    @Query("select p from Person as p inner join p.names as pn where pn.firstName = :firstName" +
            " and pn.lastName = :lastName and p.email = :email")
    Optional<Person> findByFirstLastNameAndEmail(@Param(value = "firstName") String firstName,
                                                 @Param(value = "lastName") String lastName,
                                                 @Param(value = "email") String email);

    Optional<Person> findById(Integer id);

    @Modifying
    @Query("update Person as p set p.password = :newPassword where p.id = :id")
    void changeUserPassword(@Param(value = "newPassword") String password, @Param(value = "id") Integer id);

    @Query("select ptc.countryName, pt.townName, ptca.address from Person as p" +
            " inner join p.town as pt" +
            " inner join pt.country as ptc" +
            " inner join p.town.addresses as ptca where p.id = :id")
    List<String> personAddress(@Param(value = "id") Integer id);
}
