package kharlacz.springapp.recipe;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RecipeRepo extends PagingAndSortingRepository<Recipe, Long> {
    
    List<Recipe> findTop10ByOrderByRatingDesc();
    
    @Query(
            name = "selectRecipesByCategoryId",
            nativeQuery = true,
            value = "SELECT r.* FROM recipe r JOIN recipe_to_recipe_category rtrc ON  " +
                    "r.id=rtrc.recipe_id JOIN recipe_category rc ON rtrc.recipe_category_id = rc" +
                    ".id " + 
                    "WHERE rc.id =?1"
    )
    List<Recipe> findRecipesByCategoryId(long categoryId);
}
