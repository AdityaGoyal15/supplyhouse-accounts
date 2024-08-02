package com.supplyhouse.order;

import java.time.LocalDate;
import java.util.List;

public interface OrderReadService {

  List<Order> findAllByAccountId(Long accountId);

  List<Order> findAllByAccountAndDateRange(Long accountId, LocalDate startDate, LocalDate endDate);
}
