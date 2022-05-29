package kharlacz.springapp.recipe.services;

import kharlacz.springapp.recipe.ingredient.IngredientWithQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngrWithQuantityRepo extends JpaRepository<IngredientWithQuantity, Long> {
}
