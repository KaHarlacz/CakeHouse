package kharlacz.springapp.recipe;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    int countByAuthor_Id(long id);

    @Query(
            nativeQuery = true,
            value = """
                    SELECT sum(r.rating) FROM recipe r
                    JOIN user u ON r.author_id = u.id 
                    WHERE r.author_id = ?1
                    """
    )
    int sumRatingOfUserRecipes(long userId);
    
    Optional<Recipe> findFirstByAuthor_IdOrderByRatingDesc(long userId);
}
