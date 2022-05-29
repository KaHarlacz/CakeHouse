package kharlacz.springapp.recipe.ingredient;

import io.soabase.recordbuilder.core.RecordBuilder;
import kharlacz.springapp.recipe.ingredient.unit.Unit;

@RecordBuilder
public record IngredientQuantityDto(String name, Unit unit, double quantity) {
    
    public static IngredientQuantityDto from(IngredientWithQuantity iwq) {
        return IngredientQuantityDtoBuilder.builder()
                .name(iwq.getIngredient().getName())
                .unit(iwq.getIngredient().getUnit())
                .quantity(iwq.getQuantity())
                .build();
    }
}
