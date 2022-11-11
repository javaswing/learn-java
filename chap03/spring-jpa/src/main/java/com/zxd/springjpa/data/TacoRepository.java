package com.zxd.springjpa.data;

import com.zxd.springjpa.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
