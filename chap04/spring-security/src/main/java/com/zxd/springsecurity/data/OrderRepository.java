package com.zxd.springsecurity.data;

import com.zxd.springsecurity.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
