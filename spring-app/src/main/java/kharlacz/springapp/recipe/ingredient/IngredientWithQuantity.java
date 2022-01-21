package kharlacz.springapp.recipe.ingredient;

import kharlacz.springapp.recipe.Recipe;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "recipe_to_ingredient")
@NoArgsConstructor
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
    
    private double quantity;
    
    @Embeddable
    @EqualsAndHashCode
    @Setter
    @NoArgsConstructor
    public static class IngredientWithQuantityId implements Serializable {
        @Column(name = "recipe_id")
        protected Long recipeId;
        @Column(name = "ingredient_id")
        protected Long ingredientId;
    }
}
