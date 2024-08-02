package com.supplyhouse.account.validator;

import com.supplyhouse.order.Order;
import java.util.List;

public class AccountValidator {

  private AccountValidator() {}

  public static void throwIfNotTooManyOrders(Long id, List<Order> ordersPlacedInLast12Months) {
    if (ordersPlacedInLast12Months.size() < 10) {
      throw new IllegalArgumentException(
          "The account [%d] has not placed ten or more orders in last 12 months. Hence, it can not be upgraded at the moment."
              .formatted(id));
    }
  }
}
