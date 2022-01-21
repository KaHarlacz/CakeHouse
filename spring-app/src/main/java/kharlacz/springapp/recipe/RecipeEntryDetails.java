package kharlacz.springapp.recipe;

import kharlacz.springapp.recipe.category.RecipeCategoryDto;
import kharlacz.springapp.recipe.comment.CommentDto;
import kharlacz.springapp.recipe.ingredient.IngredientDto;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeEntryDetails {
    private Long id;
    private String name;
    private String desc;
    private String author;
    private String prepMethod;
    private Integer rating;
    private Date dateAdded;
    private String imageB64;
    private Set<IngredientDto> ingredients;
    private Set<RecipeCategoryDto> categories;
    private Set<CommentDto> comments;
}
