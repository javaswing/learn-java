package taco.api;

import org.springframework.data.repository.CrudRepository;
import taco.data.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String name);
}
