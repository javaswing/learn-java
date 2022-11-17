package com.zxd.springsecurity.data;

import com.zxd.springsecurity.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String name);
}
