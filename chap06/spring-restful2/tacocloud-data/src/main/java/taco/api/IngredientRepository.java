package taco.api;

import org.springframework.data.repository.CrudRepository;
import taco.data.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
