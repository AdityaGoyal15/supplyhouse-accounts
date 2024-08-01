package com.supplyhouse.order;

import java.time.LocalDate;
import java.util.List;

public interface OrderReadService {

    List<Order> findAllByAccountAndDateRange(
            Long accountId, LocalDate startDate, LocalDate endDate);

    List<Order> findAllByAccountId(Long accountId);

    List<Order> findAllByAccountIdAndBusinessAccountId(Long businessAccountId, Long accountId);
}
