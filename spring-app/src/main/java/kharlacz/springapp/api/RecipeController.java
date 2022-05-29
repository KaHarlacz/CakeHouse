package kharlacz.springapp.api;

import kharlacz.springapp.recipe.comment.post.CommentReq;
import kharlacz.springapp.recipe.comment.post.CommentRes;
import kharlacz.springapp.recipe.ingredient.IngredientDto;
import kharlacz.springapp.recipe.services.RecipeFacadeService;
import kharlacz.springapp.recipe.services.commands.SearchRecipeCommand;
import kharlacz.springapp.recipe.services.commands.UploadRecipeCommand;
import kharlacz.springapp.recipe.services.dto.RecipeDetails;
import kharlacz.springapp.recipe.services.dto.RecipePreview;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class RecipeController {

    private final RecipeFacadeService recipeServiceFacade;

    @GetMapping("/recipes/search")
    Page<RecipePreview> searchRecipes(SearchRecipeCommand searchReqDto) {
        return recipeServiceFacade.searchRecipes(searchReqDto);
    }

    @GetMapping("/recipes/{id}")
    RecipeDetails getRecipe(@PathVariable long id) {
        return recipeServiceFacade.getRecipeDetails(id);
    }

    @GetMapping("/recipes/categories")
    List<String> getCategoryNames() {
        return recipeServiceFacade.getCategoryNames();
    }

    @GetMapping("/recipes/ingredients")
    Set<IngredientDto> getIngredient() {
        return recipeServiceFacade.getIngredients();
    }
    
    // TODO: There is possibility to comment as another user! To be fixed
    @PostMapping("/recipes/{id}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    CommentRes addComment(
            @PathVariable("id") long recipeId,
            @RequestBody CommentReq commentReq) {
        return recipeServiceFacade.commentRecipe(recipeId, commentReq);
    }

    @PostMapping("/recipes/upload")
    @ResponseStatus(HttpStatus.CREATED)
    void addRecipe(@RequestBody() UploadRecipeCommand recipeCmd) {
        recipeServiceFacade.addRecipe(recipeCmd);
    }
}
