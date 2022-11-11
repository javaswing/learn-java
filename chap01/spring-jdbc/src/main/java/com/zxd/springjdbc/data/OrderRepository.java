package com.zxd.springjdbc.data;

import com.zxd.springjdbc.model.Order;

public interface OrderRepository {
    Order save(Order order);
}
