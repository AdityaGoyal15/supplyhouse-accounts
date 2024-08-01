package com.supplyhouse.order.impl.write;

import com.supplyhouse.account.Account;
import com.supplyhouse.account.AccountReadService;
import com.supplyhouse.order.Order;
import com.supplyhouse.order.OrderRepository;
import com.supplyhouse.order.dto.PlaceOrderDTO;
import com.supplyhouse.order.OrderWriteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderWriteServiceImpl implements OrderWriteService {

  private final AccountReadService accountReadService;
  private final OrderRepository orderRepository;

  public OrderWriteServiceImpl(
      AccountReadService accountReadService, OrderRepository orderRepository) {
    this.accountReadService = accountReadService;
    this.orderRepository = orderRepository;
  }

  @Override
  @Transactional
  public Order place(PlaceOrderDTO placeOrderDTO) {
    Long accountId = placeOrderDTO.accountId();
    Account account = accountReadService.findById(accountId);
    Order newOrder = new Order();
    newOrder.create(account);
    return orderRepository.save(newOrder);
  }
}