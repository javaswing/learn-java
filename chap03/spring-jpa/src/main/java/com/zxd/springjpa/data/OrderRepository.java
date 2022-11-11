package com.zxd.springjpa.data;

import com.zxd.springjpa.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
