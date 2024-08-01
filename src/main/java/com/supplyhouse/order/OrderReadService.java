package com.supplyhouse.order;

import java.time.LocalDate;
import java.util.List;

public interface OrderReadService {

    List<Order> findOrdersByAccountAndDateRange(
            Long accountId, LocalDate startDate, LocalDate endDate);
}
