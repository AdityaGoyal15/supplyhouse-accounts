package com.supplyhouse.invitation.impl.read;

import com.supplyhouse.invitation.Invitation;
import com.supplyhouse.invitation.InvitationReadService;
import com.supplyhouse.invitation.InvitationRepository;
import org.springframework.stereotype.Service;

@Service
public class InvitationReadServiceImpl implements InvitationReadService {

  private final InvitationRepository invitationRepository;

  public InvitationReadServiceImpl(InvitationRepository invitationRepository) {
    this.invitationRepository = invitationRepository;
  }

  @Override
  public Invitation findById(Long id) {
    return invitationRepository
        .findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException("Invitation [%d] not found.".formatted(id)));
  }

  @Override
  public Invitation findBySenderIdAndReceiverId(Long businessAccountId, Long accountId) {
    return invitationRepository.findBySenderIdAndReceiverId(businessAccountId, accountId);
  }
}
