package kharlacz.springapp.recipe.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeCategoryService {

    public List<String> getCategoriesNames() {
        return Arrays.stream(RecipeCategory.values())
                .map(RecipeCategory::name)
                .collect(Collectors.toList());
    }
}
