package com.lyc.order.service;

import com.lyc.order.bean.Order;

public interface OrderService {
    Order createOrder(Long productID,Long userId);
}
