package com.supplyhouse.invitation.impl.read;

import com.supplyhouse.invitation.Invitation;
import com.supplyhouse.invitation.InvitationReadService;
import com.supplyhouse.invitation.InvitationRepository;
import java.util.List;
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
  public Invitation findLatestAcceptedBySenderIdAndReceiverId(Long senderId, Long receiverId) {
    List<Invitation> acceptedInvitations =
        invitationRepository.findAllAcceptedBySenderIdAndReceiverIdOrderByRespondedOnDesc(
            senderId, receiverId);
    return acceptedInvitations.isEmpty() ? null : acceptedInvitations.get(0);
  }

  @Override
  public Invitation findPendingInvitationBySenderIdAndReceiverId(
      Long senderId, Long receiverId, String status) {
    return invitationRepository.findPendingBySenderIdAndReceiverId(senderId, receiverId);
  }
}
