package com.supplyhouse.order.impl.read;

import static com.supplyhouse.order.validator.OrderValidator.throwIfAccountIsNotLinkedToBusinessAccount;

import com.supplyhouse.account.Account;
import com.supplyhouse.account.AccountReadService;
import com.supplyhouse.account.AccountType;
import com.supplyhouse.invitation.Invitation;
import com.supplyhouse.invitation.InvitationReadService;
import com.supplyhouse.order.Order;
import com.supplyhouse.order.OrderReadService;
import com.supplyhouse.order.OrderRepository;
import java.time.LocalDate;
import java.util.ArrayList;
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

  /**
   * Queries orders by accountId. If account is a regular account, then returns only the orders
   * placed by accountId. If account is a business account, then returns orders placed by accountId
   * as well as order placed by sub-accounts based on their consent to share all order history.
   */
  @Override
  public List<Order> findAllByAccountId(Long accountId) {
    Account account = accountReadService.findById(accountId);

    if (account.getAccountType() == AccountType.REGULAR) {
      return account.getOrders();
    }
    List<Account> subAccounts = accountReadService.findAllSubAccountsById(accountId);
    List<Order> orders = new ArrayList<>(account.getOrders());

    subAccounts.forEach(
        subAccount -> {
          LocalDate startDate = evaluateStartDate(accountId, subAccount);
          LocalDate endDate = LocalDate.now();
          List<Order> subAccountOrders =
              findAllByAccountAndDateRange(subAccount.getId(), startDate, endDate);
          orders.addAll(subAccountOrders);
        });
    return orders;
  }

  /**
   * Returns date to query orders placed on or after. Start date is calculated based on consent to
   * share all order history since the day account was created or orders starting from when account
   * was linked to business account.
   */
  private LocalDate evaluateStartDate(Long accountId, Account subAccount) {
    if (subAccount.isShareAllHistory()) {
      return subAccount.getCreatedOn();
    } else {
      Invitation invitation =
          invitationReadService.findBySenderIdAndReceiverId(accountId, subAccount.getId());
      return invitation.getRespondedOn();
    }
  }
}
