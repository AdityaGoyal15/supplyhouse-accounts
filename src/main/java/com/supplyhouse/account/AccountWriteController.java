package com.supplyhouse.account;

import com.supplyhouse.account.dto.CreateAccountDTO;
import com.supplyhouse.account.dto.UpdateAccountDTO;
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

  private final AccountWriteService accountWriteService;

  public AccountWriteController(AccountWriteService accountWriteService) {
    this.accountWriteService = accountWriteService;
  }

  @PostMapping
  public ResponseEntity<Account> create(@RequestBody CreateAccountDTO createAccountDTO) {
    return ResponseEntity.ok(accountWriteService.create(createAccountDTO));
  }

  @PutMapping("{id}")
  public ResponseEntity<Account> update(
      @PathVariable Long id, @RequestBody UpdateAccountDTO updateAccountDTO) {
    return ResponseEntity.ok(accountWriteService.update(id, updateAccountDTO));
  }

  @PutMapping("{id}/link/{businessAccountId}")
  public ResponseEntity<Account> link(@PathVariable Long id, @PathVariable Long businessAccountId) {
    return ResponseEntity.ok(accountWriteService.link(id, businessAccountId));
  }

  @PutMapping("{id}/unlink/{businessAccountId}")
  public ResponseEntity<Account> unlink(
      @PathVariable Long id, @PathVariable Long businessAccountId) {
    return ResponseEntity.ok(accountWriteService.unLink(id, businessAccountId));
  }

  @PutMapping("/{id}/upgrade")
  public ResponseEntity<Account> upgrade(@PathVariable Long id) {
    return ResponseEntity.ok(accountWriteService.upgrade(id));
  }
}
