package com.supplyhouse.account;

import java.util.List;

public interface AccountReadService {

  Account findById(Long id);

  List<Account> findAllSubAccountsById(Long id);
}
