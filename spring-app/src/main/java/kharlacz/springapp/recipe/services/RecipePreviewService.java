package kharlacz.springapp.recipe.services;

import kharlacz.springapp.recipe.Recipe;
import kharlacz.springapp.recipe.RecipeEntryDetailsMapper;
import kharlacz.springapp.recipe.RecipeRepo;
import kharlacz.springapp.recipe.services.dto.RecipeDetails;
import kharlacz.springapp.recipe.services.dto.RecipePreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static kharlacz.springapp.recipe.services.RecipeFacadeService.DEFAULT_PAGE_SIZE;

@Service
public class RecipePreviewService {

    private final RecipeRepo recipeRepo;
    
    @Autowired
    public RecipePreviewService(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }
    
    public List<RecipePreview> getNewest(int page) {
        return recipeRepo.findAll(PageRequest.of(page, DEFAULT_PAGE_SIZE))
                .stream()
                .map(RecipePreview::from)
                .collect(Collectors.toList());
    }

    public List<RecipePreview> getByCategoryId(long id, int page) {
        var recipesOfCat = recipeRepo.findRecipesByCategoryId(
                id, PageRequest.of(page, DEFAULT_PAGE_SIZE));
        return toEntryPreviews(recipesOfCat.stream());
    }

    public RecipeDetails getById(long id) {
        return recipeRepo.findById(id)
                .map(RecipeEntryDetailsMapper::map)
                .orElse(new RecipeDetails());
    }

    private List<RecipePreview> toEntryPreviews(Stream<Recipe> recipes) {
        return recipes.map(RecipePreview::from)
                .collect(Collectors.toList());
    }

    public void addRecipe() {

    }
}