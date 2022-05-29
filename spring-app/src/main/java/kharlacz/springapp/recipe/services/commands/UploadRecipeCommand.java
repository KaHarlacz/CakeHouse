package kharlacz.springapp.recipe.services.commands;

import kharlacz.springapp.recipe.ingredient.IngredientDto;

import java.util.Set;

public record UploadRecipeCommand(
        String name,
        String author,
        String desc,
        String imageB64,
        String prepMethod,
        Set<IngredientDto> ingredients,
        Set<String> categories) {
}
