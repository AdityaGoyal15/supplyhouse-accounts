package com.supplyhouse.account;

import java.time.LocalDate;
import java.util.List;

import com.supplyhouse.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {

  List<Account> findAllByBusinessAccountId(Long businessAccountId);
}
