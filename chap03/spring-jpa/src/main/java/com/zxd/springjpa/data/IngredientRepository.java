package com.zxd.springjpa.data;

import com.zxd.springjpa.model.Ingredient;

import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
