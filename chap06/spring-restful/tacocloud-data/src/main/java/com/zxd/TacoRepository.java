package com.zxd.springproperites.data;

import com.zxd.springproperites.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
