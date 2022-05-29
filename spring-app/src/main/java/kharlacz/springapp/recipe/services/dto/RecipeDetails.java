package kharlacz.springapp.recipe.services.dto;

import kharlacz.springapp.recipe.category.RecipeCategory;
import kharlacz.springapp.recipe.comment.CommentDto;
import kharlacz.springapp.recipe.ingredient.IngredientQuantityDto;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeDetails {
    private Long id;
    private String name;
    private String desc;
    private String author;
    private String prepMethod;
    private Integer rating;
    private Date dateAdded;
    private String imageB64;
    private Set<IngredientQuantityDto> ingredients;
    private Set<RecipeCategory> categories;
    private Set<CommentDto> comments;
}
