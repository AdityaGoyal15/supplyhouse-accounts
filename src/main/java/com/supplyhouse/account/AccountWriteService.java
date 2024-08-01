package com.supplyhouse.account;

import com.supplyhouse.account.dto.CreateAccountDTO;
import com.supplyhouse.account.dto.UpdateAccountDTO;

public interface AccountWriteService {

  Account create(CreateAccountDTO createAccountDTO);

  Account update(Long id, UpdateAccountDTO updateAccountDTO);

  Account link(Long id, Long businessAccountId);

  Account unLink(Long id, Long businessAccountId);

  Account upgrade(Long id);
}
