package com.example.market.App.repositories;

import com.example.market.App.domain.entities.People.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

//    @Modifying
//    @Transactional
//    @Query("select o from Orders as o")
//    List<Orders> findAll();

    @Query("select sum(o.sum) from Orders as o")
    BigDecimal turnOver();

    @Query("select sum(o.sum) from Orders as o where o.date >= :dateFrom and o.date <= :dateTo")
    Optional<BigDecimal> turOverForCurrentPeriod(@Param(value = "dateFrom") LocalDate dateFrom,
                                                @Param(value = "dateTo") LocalDate dateTo);
}
