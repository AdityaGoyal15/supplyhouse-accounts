package com.supplyhouse.order;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderReadController {

  private final OrderReadService orderReadService;

  public OrderReadController(OrderReadService orderReadService) {
    this.orderReadService = orderReadService;
  }

  @GetMapping("account/{accountId}")
  public ResponseEntity<List<Order>> findAllByAccountId(@PathVariable Long accountId) {
    return ResponseEntity.ok(orderReadService.findAllByAccountId(accountId));
  }

  @GetMapping("account/{accountId}/businessAccount/{businessAccountId}")
  public ResponseEntity<List<Order>> findAllByAccountIdAndBusinessAccountId(
      @PathVariable Long accountId, @PathVariable Long businessAccountId) {
    return ResponseEntity.ok(
        orderReadService.findAllByAccountIdAndBusinessAccountId(accountId, businessAccountId));
  }
}
