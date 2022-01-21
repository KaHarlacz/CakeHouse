package kharlacz.springapp.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    
    private final RecipeRepo recipeRepo;
    
    @Autowired
    public RecipeService(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }
    
    public List<RecipeEntryPreview> getNewest(int count) {
        return recipeRepo.findAll(PageRequest.of(0, count))
                .stream()
                .map(RecipeEntryPreviewMapper::map)
                .collect(Collectors.toList());
    }

    public List<RecipeEntryPreview> getTopRated() {
        return toEntryPreviews(recipeRepo.findTop10ByOrderByRatingDesc());
    }
    
    public List<RecipeEntryPreview> getByCategoryId(long id) {
        return toEntryPreviews(recipeRepo.findRecipesByCategoryId(id));
    }
    
    public RecipeEntryDetails getById(long id) {
        return recipeRepo.findById(id)
                .map(RecipeEntryDetailsMapper::map)
                .orElse(new RecipeEntryDetails());
    }
    
    private List<RecipeEntryPreview> toEntryPreviews(List<Recipe> recipes) {
        return recipes.stream()
                .map(RecipeEntryPreviewMapper::map)
                .collect(Collectors.toList());
    }
}