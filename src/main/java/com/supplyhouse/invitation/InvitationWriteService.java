package com.supplyhouse.invitation;

import com.supplyhouse.invitation.dto.SendInvitationDto;

public interface InvitationWriteService {

  Invitation send(SendInvitationDto sendInvitationDto);

  Invitation accept(Long id);

  Invitation decline(Long id);
}
