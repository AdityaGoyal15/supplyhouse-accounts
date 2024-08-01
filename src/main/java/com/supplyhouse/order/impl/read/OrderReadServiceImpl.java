package com.supplyhouse.order.impl.read;

import com.supplyhouse.order.Order;
import com.supplyhouse.order.OrderRepository;
import com.supplyhouse.order.OrderReadService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderReadServiceImpl implements OrderReadService {

  private final OrderRepository orderRepository;

  public OrderReadServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public List<Order> findOrdersByAccountAndDateRange(
      Long accountId, LocalDate startDate, LocalDate endDate) {
    return orderRepository.findOrdersByAccountAndDateRange(accountId, startDate, endDate);
  }
}
