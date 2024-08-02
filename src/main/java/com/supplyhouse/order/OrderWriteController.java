package com.supplyhouse.order;

import com.supplyhouse.order.dto.CreateOrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderWriteController {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderWriteController.class);
  private final OrderWriteService orderWriteService;

  public OrderWriteController(OrderWriteService orderWriteService) {
    this.orderWriteService = orderWriteService;
  }

  @PostMapping
  public ResponseEntity<Order> create(@RequestBody CreateOrderDTO createOrderDTO) {
    try {
      return ResponseEntity.ok(orderWriteService.create(createOrderDTO));
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return ResponseEntity.badRequest().build();
    }
  }
}
