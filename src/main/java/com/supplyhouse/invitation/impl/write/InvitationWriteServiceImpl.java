package com.supplyhouse.invitation.impl.write;

import com.supplyhouse.account.Account;
import com.supplyhouse.account.AccountReadService;
import com.supplyhouse.account.AccountType;
import com.supplyhouse.exception.PreconditionFailedException;
import com.supplyhouse.invitation.Invitation;
import com.supplyhouse.invitation.InvitationReadService;
import com.supplyhouse.invitation.InvitationRepository;
import com.supplyhouse.invitation.InvitationWriteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InvitationWriteServiceImpl implements InvitationWriteService {

  private final InvitationRepository invitationRepository;
  private final AccountReadService accountReadService;
  private final InvitationReadService invitationReadService;

  public InvitationWriteServiceImpl(
      InvitationRepository invitationRepository,
      AccountReadService accountReadService,
      InvitationReadService invitationReadService) {
    this.invitationRepository = invitationRepository;
    this.accountReadService = accountReadService;
    this.invitationReadService = invitationReadService;
  }

  @Override
  @Transactional
  public Invitation send(Long senderId, Long receiverId) {
    Account sender = accountReadService.findById(senderId);
    Account receiver = accountReadService.findById(receiverId);

    if (sender.getAccountType() != AccountType.BUSINESS) {
      throw new PreconditionFailedException(
          "PRECONDITION_FAILED",
          "Account [%d] is not a business account. Hence, they can not send an invitation to account [%d]."
              .formatted(senderId, receiverId));
    }
    Invitation invitation = new Invitation();
    invitation.create(sender, receiver);
    return invitationRepository.save(invitation);
  }

  @Override
  @Transactional
  public Invitation accept(Long id) {
    Invitation invitation = invitationReadService.findById(id);
    invitation.accept();
    return invitationRepository.save(invitation);
  }

  @Override
  @Transactional
  public Invitation decline(Long id) {
    Invitation invitation = invitationReadService.findById(id);
    invitation.decline();
    return invitationRepository.save(invitation);
  }
}
