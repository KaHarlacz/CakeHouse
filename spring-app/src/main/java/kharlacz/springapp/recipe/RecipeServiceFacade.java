package kharlacz.springapp.recipe;

import kharlacz.springapp.recipe.category.RecipeCategoryDto;
import kharlacz.springapp.recipe.category.RecipeCategoryService;
import kharlacz.springapp.recipe.comment.CommentDto;
import kharlacz.springapp.recipe.comment.CommentService;
import kharlacz.springapp.recipe.comment.post.CommentPost;
import kharlacz.springapp.recipe.comment.post.CommentPostResponse;
import kharlacz.springapp.user.authentication.BasicAuthString;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RecipeServiceFacade {
    
    private final RecipeService recipeService;
    private final RecipeCategoryService recipeCategoryService;
    private final CommentService commentService;
    
    public List<RecipeEntryPreview> getNewestRecipes(int count) {
        return recipeService.getNewest(count);
    }
    
    public RecipeEntryDetails getRecipeDetails(long recipeId) {
        return recipeService.getById(recipeId);
    }

    public List<RecipeEntryPreview> getRecipesOfCategory(long categoryId) {
        return recipeService.getByCategoryId(categoryId);
    }
    
    public List<RecipeCategoryDto> getRecipesCategories() {
        return recipeCategoryService.getCategories();
    }
    
    public CommentPostResponse commentRecipe(CommentPost comment, BasicAuthString creditentials) {
        return commentService.addComment(comment, creditentials);
    }
    
    public List<CommentDto> getCommentsForRecipe(long recipeId) {
        return commentService.getCommentsForRecipe(recipeId);
    }
}
