package com.zxd.springsecurity.data;

import com.zxd.springsecurity.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
