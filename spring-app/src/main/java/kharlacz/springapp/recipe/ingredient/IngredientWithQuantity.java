package kharlacz.springapp.recipe.ingredient;

import kharlacz.springapp.recipe.Recipe;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity(name = "recipe_to_ingredient")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class IngredientWithQuantity {
    @EmbeddedId
    private IngredientWithQuantityId id;
    
    @ManyToOne
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private Recipe recipe;
    
    @ManyToOne
    @JoinColumn(name = "ingredient_id", insertable = false, updatable = false)
    private Ingredient ingredient;
    
    @Min(0)
    private double quantity;
    
    public static IngredientWithQuantity from(
            Recipe recipe, Ingredient ingr, double quantity) {
        var id = new IngredientWithQuantityId(recipe.getId(), ingr.getId());
        return new IngredientWithQuantity(id, recipe, ingr, quantity);
    }
    
    @Embeddable
    @EqualsAndHashCode
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IngredientWithQuantityId implements Serializable {
        @Column(name = "recipe_id")
        protected Long recipeId;
        @Column(name = "ingredient_id")
        protected Long ingredientId;
    }
}
