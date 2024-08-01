package com.supplyhouse.invitation.impl.write;

import static com.supplyhouse.invitation.validator.InvitationValidator.throwIdSenderAndReceiverAreSame;
import static com.supplyhouse.invitation.validator.InvitationValidator.throwIfInvitationStatusIsNotPending;
import static com.supplyhouse.invitation.validator.InvitationValidator.throwIfSenderIsNotBusiness;

import com.supplyhouse.account.Account;
import com.supplyhouse.account.AccountReadService;
import com.supplyhouse.invitation.Invitation;
import com.supplyhouse.invitation.InvitationReadService;
import com.supplyhouse.invitation.InvitationRepository;
import com.supplyhouse.invitation.InvitationWriteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InvitationWriteServiceImpl implements InvitationWriteService {

  private final InvitationRepository invitationRepository;
  private final InvitationReadService invitationReadService;
  private final AccountReadService accountReadService;

  public InvitationWriteServiceImpl(
      InvitationRepository invitationRepository,
      InvitationReadService invitationReadService,
      AccountReadService accountReadService) {
    this.invitationRepository = invitationRepository;
    this.invitationReadService = invitationReadService;
    this.accountReadService = accountReadService;
  }

  @Override
  @Transactional
  public Invitation send(Long senderId, Long receiverId) {
    throwIdSenderAndReceiverAreSame(senderId, receiverId);
    Account sender = accountReadService.findById(senderId);
    throwIfSenderIsNotBusiness(senderId, receiverId, sender);
    Account receiver = accountReadService.findById(receiverId);
    Invitation invitation = new Invitation();
    invitation.create(sender, receiver);
    return invitationRepository.save(invitation);
  }

  @Override
  @Transactional
  public Invitation accept(Long id) {
    Invitation invitation = invitationReadService.findById(id);
    throwIfInvitationStatusIsNotPending(invitation);
    invitation.accept();
    // accountWriteService.link(invitation.getReceiver().getId(), invitation.getSender().getId());
    return invitationRepository.save(invitation);
  }

  @Override
  @Transactional
  public Invitation decline(Long id) {
    Invitation invitation = invitationReadService.findById(id);
    throwIfInvitationStatusIsNotPending(invitation);
    invitation.decline();
    return invitationRepository.save(invitation);
  }
}
