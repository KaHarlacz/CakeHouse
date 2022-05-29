package kharlacz.springapp.recipe.services.commands;

import kharlacz.springapp.recipe.category.RecipeCategory;
import kharlacz.springapp.recipe.ingredient.IngredientQuantityDto;

import java.util.Set;

public record UploadRecipeCommand(
        String name,
        String desc,
        String imageB64,
        String prepMethod,
        RecipeCategory category,
        Set<IngredientQuantityDto> ingredients
) {
}
