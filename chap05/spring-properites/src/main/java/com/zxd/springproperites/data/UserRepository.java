package com.zxd.springproperites.data;

import com.zxd.springproperites.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String name);
}
