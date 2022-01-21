package kharlacz.springapp.recipe.category;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeCategoryRepo extends PagingAndSortingRepository<RecipeCategory, Long> {

}

