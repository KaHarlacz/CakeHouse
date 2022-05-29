package kharlacz.springapp.recipe.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeCategoryService {

    private final RecipeCategoryRepo categoryRepo;

    public List<String> getCategoriesNames() {
        return categoryRepo.findAll().stream()
                .map(RecipeCategory::getName)
                .collect(Collectors.toList());
    }
}
