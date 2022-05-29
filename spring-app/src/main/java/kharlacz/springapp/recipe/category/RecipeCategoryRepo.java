package kharlacz.springapp.recipe.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeCategoryRepo extends JpaRepository<RecipeCategory, Long> {

}

