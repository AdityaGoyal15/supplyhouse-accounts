package com.supplyhouse.invitation;

public interface InvitationReadService {

  Invitation findById(Long id);

  Invitation findBySenderIdAndReceiverId(Long businessAccountId, Long accountId);
}
