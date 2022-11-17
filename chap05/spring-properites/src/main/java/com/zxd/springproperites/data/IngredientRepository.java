package com.zxd.springproperites.data;

import com.zxd.springproperites.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
