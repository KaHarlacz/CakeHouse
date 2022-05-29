package kharlacz.springapp.recipe.services;

import kharlacz.springapp.recipe.category.RecipeCategoryService;
import kharlacz.springapp.recipe.comment.CommentDto;
import kharlacz.springapp.recipe.comment.CommentService;
import kharlacz.springapp.recipe.comment.post.CommentReq;
import kharlacz.springapp.recipe.comment.post.CommentRes;
import kharlacz.springapp.recipe.services.commands.UploadRecipeCommand;
import kharlacz.springapp.recipe.services.dto.RecipeDetails;
import kharlacz.springapp.recipe.services.dto.RecipePreview;
import kharlacz.springapp.recipe.services.commands.SearchRecipeCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeFacadeService {

    public static int DEFAULT_PAGE_SIZE = 5;

    private final SearchRecipeService searchRecipeService;
    private final RecipeImageService recipeImageService;
    private final RecipePreviewService recipeService;
    private final RecipeCategoryService recipeCategoryService;
    private final RecipeUploadService recipeUploadService;
    private final CommentService commentService;
    
    public List<RecipePreview> getNewestRecipes(int page) {
        return recipeService.getNewest(page);
    }
    
    public RecipeDetails getRecipeDetails(long recipeId) {
        return recipeService.getById(recipeId);
    }

    public Page<RecipePreview> searchRecipes(SearchRecipeCommand searchReq) {
        return searchRecipeService.search(searchReq);
    }
    
    public byte[] getRecipeImage(long recipeId) {
        return recipeImageService.getRecipeImage(recipeId);
    }
    
    public List<String> getCategoriesNames() {
        return recipeCategoryService.getCategoriesNames();
    }
    
    public CommentRes commentRecipe(long recipeId, CommentReq commentReq) {
        return commentService.addComment(recipeId, commentReq);
    }
    
    public List<CommentDto> getCommentsForRecipe(long recipeId) {
        return commentService.getCommentsForRecipe(recipeId);
    }

    public void addRecipe(UploadRecipeCommand recipeCmd) {
        recipeUploadService.upload(recipeCmd);
    }
}
