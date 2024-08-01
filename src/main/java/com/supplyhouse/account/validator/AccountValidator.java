package com.supplyhouse.account.validator;

import com.supplyhouse.account.Account;
import com.supplyhouse.account.AccountType;
import com.supplyhouse.order.Order;
import java.util.List;

public class AccountValidator {

  private AccountValidator() {}

  public static void throwIfNotBusinessAccount(Account businessAccount) {
    if (businessAccount.getAccountType() != AccountType.BUSINESS) {
      throw new IllegalArgumentException(
          "The account [%d] is not a business account".formatted(businessAccount.getId()));
    }
  }

  public static void throwIfAlreadyLinked(Long businessAccountId, Account account) {
    if (account.getBusinessAccountId() != null) {
      throw new IllegalArgumentException(
          ("Account [%d] is linked to business account [%d]. Hence, it can not be linked to another business account [%d].")
              .formatted(account.getId(), account.getBusinessAccountId(), businessAccountId));
    }
  }

  public static void throwIfNotLinked(Long id, Long businessAccountId, Account account) {
    if (!account.getBusinessAccountId().equals(businessAccountId)) {
      throw new IllegalArgumentException(
          "The account [%d] is not linked to business account [%d]. Hence, it can not be unlinked."
              .formatted(id, businessAccountId));
    }
  }

  public static void throwIfNotTooManyOrders(Long id, List<Order> ordersPlacedInLast12Months) {
    if (ordersPlacedInLast12Months.size() < 10) {
      throw new IllegalArgumentException(
          "The account [%d] has not placed ten or more orders in last 12 months. Hence, it can not be upgraded at the moment."
              .formatted(id));
    }
  }
}
