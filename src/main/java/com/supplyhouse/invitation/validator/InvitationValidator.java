package com.supplyhouse.invitation.validator;

import com.supplyhouse.account.Account;
import com.supplyhouse.account.AccountType;
import com.supplyhouse.exception.PreconditionFailedException;

public class InvitationValidator {

  public static final String PRECONDITION_FAILED = "PRECONDITION_FAILED";

  private InvitationValidator() {}

  public static void throwIfSenderIsNotBusiness(Long senderId, Long receiverId, Account sender) {
    if (sender.getAccountType() != AccountType.BUSINESS) {
      throw new PreconditionFailedException(
          PRECONDITION_FAILED,
          "Account [%d] is not a business account. Hence, they can not send an invitation to account [%d]."
              .formatted(senderId, receiverId));
    }
  }

  public static void throwIdSenderAndReceiverAreSame(Long senderId, Long receiverId) {
    if (senderId.equals(receiverId)) {
      throw new PreconditionFailedException(
          PRECONDITION_FAILED,
          "The sender [%d] and receiver [%d] accounts can not be same."
              .formatted(senderId, receiverId));
    }
  }

  public static void throwIfReceiverAlreadyLinkedToBusiness(
      Long senderId, Long linkedTo, Long receiverId) {
    if (linkedTo != null) {
      throw new PreconditionFailedException(
          PRECONDITION_FAILED,
          "The receiver [%d] is already linked to [%d]. Hence, can not accept an invitation from sender [%d]."
              .formatted(receiverId, linkedTo, senderId));
    }
  }
}
