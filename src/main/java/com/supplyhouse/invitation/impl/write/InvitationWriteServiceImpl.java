package com.supplyhouse.invitation.impl.write;

import static com.supplyhouse.invitation.validator.InvitationValidator.throwIfInvitationStatusIsNotPending;
import static com.supplyhouse.invitation.validator.InvitationValidator.throwIfReceiverIsABusiness;
import static com.supplyhouse.invitation.validator.InvitationValidator.throwIfSenderAndReceiverAreSame;
import static com.supplyhouse.invitation.validator.InvitationValidator.throwIfSenderIsNotABusiness;

import com.supplyhouse.account.Account;
import com.supplyhouse.account.AccountReadService;
import com.supplyhouse.invitation.Invitation;
import com.supplyhouse.invitation.InvitationReadService;
import com.supplyhouse.invitation.InvitationRepository;
import com.supplyhouse.invitation.InvitationStatus;
import com.supplyhouse.invitation.InvitationWriteService;
import com.supplyhouse.invitation.dto.SendInvitationDto;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
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
  public Invitation send(SendInvitationDto sendInvitationDto) {
    Long senderId = sendInvitationDto.senderId();
    Long receiverId = sendInvitationDto.receiverId();
    Invitation pendingInvitation = findPendingInvitation(senderId, receiverId);

    if (pendingInvitation != null) {
      pendingInvitation.setSentOn(LocalDate.now());
      return invitationRepository.save(pendingInvitation);
    }
    throwIfSenderAndReceiverAreSame(senderId, receiverId);
    Account sender = accountReadService.findById(senderId);
    throwIfSenderIsNotABusiness(receiverId, sender);
    Account receiver = accountReadService.findById(receiverId);
    throwIfReceiverIsABusiness(senderId, receiver);
    Invitation invitation = new Invitation();
    invitation.create(sender, receiver);
    return invitationRepository.save(invitation);
  }

  private Invitation findPendingInvitation(Long senderId, Long receiverId) {
    return invitationReadService.findPendingInvitationBySenderIdAndReceiverId(
        senderId, receiverId, InvitationStatus.PENDING.name());
  }

  @Override
  @Transactional
  public Invitation accept(Long id) {
    Invitation invitation = invitationReadService.findById(id);
    throwIfInvitationStatusIsNotPending(invitation);
    invitation.accept();
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
