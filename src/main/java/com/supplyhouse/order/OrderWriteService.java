package com.supplyhouse.order;

import com.supplyhouse.order.dto.PlaceOrderDTO;

public interface OrderWriteService {

  Order place(PlaceOrderDTO order);
}
