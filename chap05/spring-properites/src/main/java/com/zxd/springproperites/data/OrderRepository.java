package com.zxd.springproperites.data;

import com.zxd.springproperites.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
