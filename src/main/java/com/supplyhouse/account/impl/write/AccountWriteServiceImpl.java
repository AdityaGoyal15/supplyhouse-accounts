package com.supplyhouse.account.impl.write;

import static com.supplyhouse.account.validator.AccountValidator.throwIfNotTooManyOrders;

import com.supplyhouse.account.Account;
import com.supplyhouse.account.AccountReadService;
import com.supplyhouse.account.AccountRepository;
import com.supplyhouse.account.AccountType;
import com.supplyhouse.account.AccountWriteService;
import com.supplyhouse.account.dto.CreateAccountDTO;
import com.supplyhouse.account.dto.UpdateAccountDTO;
import com.supplyhouse.order.Order;
import com.supplyhouse.order.OrderReadService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AccountWriteServiceImpl implements AccountWriteService {

  private final AccountRepository accountRepository;
  private final AccountReadService accountReadService;
  private final OrderReadService orderReadService;

  public AccountWriteServiceImpl(
      AccountRepository accountRepository,
      AccountReadService accountReadService,
      OrderReadService orderReadService) {
    this.accountRepository = accountRepository;
    this.accountReadService = accountReadService;
    this.orderReadService = orderReadService;
  }

  @Override
  @Transactional
  public Account create(CreateAccountDTO createAccountDTO) {
    Account account = new Account();
    account.create(createAccountDTO);
    return accountRepository.save(account);
  }

  @Override
  @Transactional
  public Account update(Long id, UpdateAccountDTO updateAccountDTO) {
    Account account = accountReadService.findById(id);
    account.update(updateAccountDTO);
    return accountRepository.save(account);
  }

  @Override
  @Transactional
  public Account upgrade(Long id) {
    Account account = accountReadService.findById(id);

    if (account.getAccountType() == AccountType.BUSINESS) {
      return account;
    }
    LocalDate today = LocalDate.now();
    List<Order> ordersPlacedInLast12Months =
        orderReadService.findAllByAccountAndDateRange(id, today.minusMonths(12), today);
    throwIfNotTooManyOrders(id, ordersPlacedInLast12Months);
    account.upgrade();
    return accountRepository.save(account);
  }

  @Override
  @Transactional
  public Account unLink(Long id) {
    Account account = accountReadService.findById(id);
    account.unlink();
    return accountRepository.save(account);
  }
}
