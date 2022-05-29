package kharlacz.springapp.recipe.services;

import kharlacz.springapp.recipe.Recipe;
import kharlacz.springapp.recipe.RecipeRepo;
import kharlacz.springapp.recipe.ingredient.Ingredient;
import kharlacz.springapp.recipe.ingredient.IngredientQuantityDto;
import kharlacz.springapp.recipe.ingredient.IngredientRepo;
import kharlacz.springapp.recipe.ingredient.IngredientWithQuantity;
import kharlacz.springapp.recipe.services.commands.UploadRecipeCommand;
import kharlacz.springapp.user.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Service
@AllArgsConstructor
public class RecipeUploadService {
    private final RecipeRepo recipeRepo;
    private final IngredientRepo ingredientRepo;
    private final IngrWithQuantityRepo ingrWithQuantityRepo;
    private final UserRepo userRepo;

    public void upload(UploadRecipeCommand uploadCmd) {
        var recipe = Recipe.from(uploadCmd);
        var username = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        var author = userRepo.findByUsername(username)
                .orElseThrow();
        recipe.setAuthor(author);
        recipe = recipeRepo.save(recipe);
        
        var ingredients = mapToIngredientsWithQuantity(
                uploadCmd.ingredients(), recipe);
        ingrWithQuantityRepo.saveAll(ingredients);
        
        recipe.setIngredients(ingredients);
        recipeRepo.save(recipe);
    }

    private Set<IngredientWithQuantity> mapToIngredientsWithQuantity(
            Set<IngredientQuantityDto> ingredients, Recipe recipe) {
        var availableIngrs = ingredientRepo.findAll()
                .stream().collect(toMap(Ingredient::getName, x -> x));
        return ingredients.stream()
                .map(iqDto -> 
                    IngredientWithQuantity.from(
                            recipe, availableIngrs.get(iqDto.name()), iqDto.quantity())
                ).collect(Collectors.toSet());
    }
}
