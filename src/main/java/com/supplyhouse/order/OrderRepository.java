package com.supplyhouse.order;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query(
      "SELECT o FROM Order o WHERE o.account.id= :accountId AND o.placedOn BETWEEN :startDate AND :endDate")
  List<Order> findAllByAccountAndDateRange(
      @Param("accountId") Long accountId,
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate);
}
