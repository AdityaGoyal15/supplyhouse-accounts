package com.supplyhouse.account;

import com.supplyhouse.account.dto.CreateAccountDTO;
import com.supplyhouse.account.dto.UpdateAccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts")
public class AccountWriteController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AccountWriteController.class);
  private final AccountWriteService accountWriteService;

  public AccountWriteController(AccountWriteService accountWriteService) {
    this.accountWriteService = accountWriteService;
  }

  @PostMapping
  public ResponseEntity<Account> create(@RequestBody CreateAccountDTO createAccountDTO) {
    try {
      return ResponseEntity.ok(accountWriteService.create(createAccountDTO));
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("{id}")
  public ResponseEntity<Account> update(
      @PathVariable Long id, @RequestBody UpdateAccountDTO updateAccountDTO) {
    try {
      return ResponseEntity.ok(accountWriteService.update(id, updateAccountDTO));
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("{id}/unlink")
  public ResponseEntity<Account> unlink(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(accountWriteService.unLink(id));
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/{id}/upgrade")
  public ResponseEntity<Account> upgrade(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(accountWriteService.upgrade(id));
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return ResponseEntity.badRequest().build();
    }
  }
}
