package kharlacz.springapp.recipe.ingredient;

public class IngredientDtoMapper {

    public static IngredientDto map(IngredientWithQuantity iwq) {
        return IngredientDto.builder()
                .id(iwq.getIngredient().getId())
                .name(iwq.getIngredient().getName())
                .unit(iwq.getIngredient().getUnit().getName())
                .quantity(iwq.getQuantity())
                .build();
    }
}
