package com.zxd.springjpa.data;

import com.zxd.springjpa.model.Order;

public interface OrderRepository {
    Order save(Order order);
}
