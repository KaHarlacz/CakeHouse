package kharlacz.springapp.recipe.ingredient;

import kharlacz.springapp.recipe.RecipeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IngredientService {

    private final RecipeRepo recipeRepo;

    // TODO: Temporary solution, ingredients should be enums
    public Set<IngredientDto> getIngredients() {
        return recipeRepo.findAllAvailableIngredients()
                .stream().map(IngredientDto::from)
                .collect(Collectors.toSet());
    }
}
