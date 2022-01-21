package kharlacz.springapp.recipe.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RecipeCategoryService {

    private final RecipeCategoryRepo categoryRepo;

    public List<RecipeCategoryDto> getCategories() {
        final var categories = new ArrayList<RecipeCategoryDto>();
        categoryRepo.findAll().forEach(c ->
                categories.add(new RecipeCategoryDto(c.getId(), c.getName()))
        );
        return categories;
    }
}
