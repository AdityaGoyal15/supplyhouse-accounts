package com.supplyhouse.invitation.validator;

import com.supplyhouse.account.Account;
import com.supplyhouse.account.AccountType;
import com.supplyhouse.invitation.Invitation;
import com.supplyhouse.invitation.InvitationStatus;

public class InvitationValidator {

  private InvitationValidator() {}

  public static void throwIfSenderIsNotBusiness(Long senderId, Long receiverId, Account sender) {
    if (sender.getAccountType() != AccountType.BUSINESS) {
      throw new IllegalArgumentException(
          "Account [%d] is not a business account. Hence, they can not send an invitation to account [%d]."
              .formatted(senderId, receiverId));
    }
  }

  public static void throwIdSenderAndReceiverAreSame(Long senderId, Long receiverId) {
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
}
