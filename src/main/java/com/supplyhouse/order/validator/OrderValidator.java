package com.supplyhouse.order.validator;

import com.supplyhouse.account.Account;
import com.supplyhouse.exception.PreconditionFailedException;

public class OrderValidator {

  private OrderValidator() {}

  public static void throwIfAccountIsNotLinkedToBusinessAccount(
      Long businessAccountId, Account account) {
    if (!businessAccountId.equals(account.getBusinessAccountId())) {
      throw new PreconditionFailedException(
          "PRECONDITION_FAILED",
          "The account [%d] is not linked to business account [%d]. Hence, its orders can not be queried by business account [%d]."
              .formatted(account.getId(), businessAccountId, businessAccountId));
    }
  }
}
