package com.supplyhouse.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts")
public class AccountReadController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AccountReadController.class);
  private final AccountReadService accountReadService;

  public AccountReadController(AccountReadService accountReadService) {
    this.accountReadService = accountReadService;
  }

  @GetMapping("{id}")
  public ResponseEntity<Account> findById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(accountReadService.findById(id));
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      return ResponseEntity.notFound().build();
    }
  }
}
