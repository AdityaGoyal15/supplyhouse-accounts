package com.supplyhouse.order.impl.read;

import static com.supplyhouse.order.validator.OrderValidator.throwIfAccountIsNotLinkedToBusinessAccount;

import com.supplyhouse.account.Account;
import com.supplyhouse.account.AccountReadService;
import com.supplyhouse.invitation.Invitation;
import com.supplyhouse.invitation.InvitationReadService;
import com.supplyhouse.order.Order;
import com.supplyhouse.order.OrderReadService;
import com.supplyhouse.order.OrderRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderReadServiceImpl implements OrderReadService {

  private final OrderRepository orderRepository;
  private final AccountReadService accountReadService;
  private final InvitationReadService invitationReadService;

  public OrderReadServiceImpl(
      OrderRepository orderRepository,
      AccountReadService accountReadService,
      InvitationReadService invitationReadService) {
    this.orderRepository = orderRepository;
    this.accountReadService = accountReadService;
    this.invitationReadService = invitationReadService;
  }

  @Override
  public List<Order> findAllByAccountAndDateRange(
      Long accountId, LocalDate startDate, LocalDate endDate) {
    return orderRepository.findAllByAccountAndDateRange(accountId, startDate, endDate);
  }

  @Override
  public List<Order> findAllByAccountId(Long accountId) {
    return orderRepository.findAllByAccountId(accountId);
  }

  @Override
  public List<Order> findAllByAccountIdAndBusinessAccountId(
      Long accountId, Long businessAccountId) {
    Account account = accountReadService.findById(accountId);
    throwIfAccountIsNotLinkedToBusinessAccount(businessAccountId, account);
    LocalDate startDate = evaluateStartDate(businessAccountId, account);
    LocalDate endDate = LocalDate.now();
    return orderRepository.findAllByBusinessAccountIdAndDateRange(
        businessAccountId, startDate, endDate);
  }

  /**
   * Calculates date to query orders placed on or after. Start date is calculated based on consent
   * to share all order history since the day account was created or orders starting from when
   * account was linked to business account.
   */
  private LocalDate evaluateStartDate(Long businessAccountId, Account account) {
    if (account.isShareAllHistory()) {
      return account.getCreatedOn();
    } else {
      Invitation invitation =
          invitationReadService.findBySenderIdAndReceiverId(businessAccountId, account.getId());
      return invitation.getRespondedOn();
    }
  }
}
