package kharlacz.springapp.api;

import kharlacz.springapp.recipe.RecipeEntryDetails;
import kharlacz.springapp.recipe.RecipeEntryPreview;
import kharlacz.springapp.recipe.RecipeServiceFacade;
import kharlacz.springapp.recipe.category.RecipeCategoryDto;
import kharlacz.springapp.recipe.comment.CommentDto;
import kharlacz.springapp.recipe.comment.post.CommentPost;
import kharlacz.springapp.recipe.comment.post.CommentPostResponse;
import kharlacz.springapp.user.authentication.BasicAuthString;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class RecipeController {

    private final RecipeServiceFacade recipeServiceFacade;

    @GetMapping("/recipes/newest")
    List<RecipeEntryPreview> getNewestRecipes(
            @RequestParam(required = false, defaultValue = "15") int count) {
        return recipeServiceFacade.getNewestRecipes(count);
    }

    @GetMapping("/recipe/{id}")
    RecipeEntryDetails getRecipe(@PathVariable long id) {
        return recipeServiceFacade.getRecipeDetails(id);
    }

    @GetMapping("/recipes/category/{id}")
    List<RecipeEntryPreview> getRecipesByCategory(@PathVariable long id) {
        return recipeServiceFacade.getRecipesOfCategory(id);
    }

    @GetMapping("/categories")
    List<RecipeCategoryDto> getCategoriesNames() {
        return recipeServiceFacade.getRecipesCategories();
    }

    @PostMapping("/recipe/comment")
    @ResponseBody
    CommentPostResponse addComment(
            @RequestBody CommentPost comment,
            @RequestHeader(name = "authorization") BasicAuthString creditentials) {
        return recipeServiceFacade.commentRecipe(comment, creditentials);
    }

    @GetMapping("/recipe/{id}/comments")
    List<CommentDto> getCommentsForRecipe(@PathVariable long id) {
        return recipeServiceFacade.getCommentsForRecipe(id);
    }
}
