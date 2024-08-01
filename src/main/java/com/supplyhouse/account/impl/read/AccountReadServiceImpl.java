package com.supplyhouse.account.impl.read;

import com.supplyhouse.account.Account;
import com.supplyhouse.account.AccountReadService;
import com.supplyhouse.account.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountReadServiceImpl implements AccountReadService {

  private final AccountRepository accountRepository;

  public AccountReadServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public Account findById(Long id) {
    return accountRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Account ID [%d] not found".formatted(id)));
  }
}
