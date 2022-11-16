package com.zxd.springsecurity.data;

import com.zxd.springsecurity.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
