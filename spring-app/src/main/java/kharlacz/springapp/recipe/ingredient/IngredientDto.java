package kharlacz.springapp.recipe.ingredient;

import kharlacz.springapp.recipe.ingredient.unit.Unit;

public record IngredientDto(String name, Unit unit) {
    
    public static IngredientDto from(IngredientWithQuantity ingredientWithQuantity) {
        var ingredient = ingredientWithQuantity.getIngredient();
        return new IngredientDto(ingredient.getName(), ingredient.getUnit());
    }
}
