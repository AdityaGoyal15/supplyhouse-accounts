package com.supplyhouse.invitation;


public interface InvitationReadService {

  Invitation findById(Long id);

  Invitation findLatestAcceptedBySenderIdAndReceiverId(Long senderId, Long receiverId);

  Invitation findPendingInvitationBySenderIdAndReceiverId(
      Long senderId, Long receiverId, String status);
}
