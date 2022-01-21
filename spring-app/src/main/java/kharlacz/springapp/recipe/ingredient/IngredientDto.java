package kharlacz.springapp.recipe.ingredient;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientDto {
    private Long id;
    private String name;
    private String unit;
    private double quantity;
}
