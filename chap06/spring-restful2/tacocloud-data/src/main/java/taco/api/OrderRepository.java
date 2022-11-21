package taco.api;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import taco.data.Order;
import taco.data.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
