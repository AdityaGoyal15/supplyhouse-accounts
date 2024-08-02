package com.supplyhouse.account;

import com.supplyhouse.account.dto.CreateAccountDTO;
import com.supplyhouse.account.dto.UpdateAccountDTO;

public interface AccountWriteService {

  Account create(CreateAccountDTO createAccountDTO);

  Account update(Long id, UpdateAccountDTO updateAccountDTO);

  Account upgrade(Long id);

  Account unLink(Long id);
}
