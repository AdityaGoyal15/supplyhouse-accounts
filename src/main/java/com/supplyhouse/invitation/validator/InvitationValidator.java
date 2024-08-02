package com.supplyhouse.invitation.validator;

import com.supplyhouse.account.Account;
import com.supplyhouse.account.AccountType;
import com.supplyhouse.invitation.Invitation;
import com.supplyhouse.invitation.InvitationStatus;

public class InvitationValidator {

  private InvitationValidator() {}

  public static void throwIfSenderIsNotABusiness(Long receiverId, Account sender) {
    if (sender.getAccountType() != AccountType.BUSINESS) {
      throw new IllegalArgumentException(
          "Account [%d] is not a business account. Hence, they can not send an invitation to account [%d]."
              .formatted(sender.getId(), receiverId));
    }
  }

  public static void throwIfSenderAndReceiverAreSame(Long senderId, Long receiverId) {
    if (senderId.equals(receiverId)) {
      throw new IllegalArgumentException(
          "The sender [%d] and receiver [%d] accounts can not be same."
              .formatted(senderId, receiverId));
    }
  }

  public static void throwIfInvitationStatusIsNotPending(Invitation invitation) {
    if (invitation.getStatus() != InvitationStatus.PENDING) {
      throw new IllegalArgumentException(
          "The valid status to accept/decline an invitation is PENDING.");
    }
  }

  public static void throwIfReceiverIsABusiness(Long senderId, Account receiver) {
    if (receiver.getAccountType() == AccountType.BUSINESS) {
      throw new IllegalArgumentException(
          "The business account [%d] can not invite another business account [%d] to be part of their business."
              .formatted(senderId, receiver.getId()));
    }
  }
}
