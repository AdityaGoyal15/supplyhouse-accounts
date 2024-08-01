package com.supplyhouse.invitation;

public interface InvitationWriteService {

  Invitation send(Long senderId, Long receiverId);

  Invitation accept(Long id);

  Invitation decline(Long id);
}
