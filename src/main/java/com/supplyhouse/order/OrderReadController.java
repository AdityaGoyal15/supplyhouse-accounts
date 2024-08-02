package com.supplyhouse.order;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderReadController {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderReadController.class);
  private final OrderReadService orderReadService;

  public OrderReadController(OrderReadService orderReadService) {
    this.orderReadService = orderReadService;
  }

  @GetMapping("account/{accountId}")
  public ResponseEntity<List<Order>> findAllByAccountId(@PathVariable Long accountId) {
    try {
      return ResponseEntity.ok(orderReadService.findAllByAccountId(accountId));
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return ResponseEntity.notFound().build();
    }
  }
}
