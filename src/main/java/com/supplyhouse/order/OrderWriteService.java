package com.supplyhouse.order;

import com.supplyhouse.order.dto.CreateOrderDTO;

public interface OrderWriteService {

  Order create(CreateOrderDTO createOrderDTO);
}
