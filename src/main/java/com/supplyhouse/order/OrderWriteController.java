package com.supplyhouse.order;

import com.supplyhouse.order.dto.PlaceOrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderWriteController {

  private final OrderWriteService orderWriteService;

  public OrderWriteController(OrderWriteService orderWriteService) {
    this.orderWriteService = orderWriteService;
  }

  @PostMapping
  public ResponseEntity<Order> create(@RequestBody PlaceOrderDTO placeOrderDTO) {
    try {
      return ResponseEntity.ok(orderWriteService.create(placeOrderDTO));
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
