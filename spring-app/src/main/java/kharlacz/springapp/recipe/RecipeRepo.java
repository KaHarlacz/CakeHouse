package kharlacz.springapp.recipe;

import kharlacz.springapp.recipe.category.RecipeCategory;
import kharlacz.springapp.recipe.ingredient.Ingredient;
import kharlacz.springapp.recipe.ingredient.IngredientWithQuantity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// TODO: Split to SearchRecipesRepo, RecipeImageRepo and UserStatsRepo
@Repository
public interface RecipeRepo extends PagingAndSortingRepository<Recipe, Long> {

    @Query(
            name = "selectRecipesByCategoryId",
            nativeQuery = true,
            value = "SELECT * FROM recipe r JOIN recipe_to_recipe_category rtrc ON  " +
                    "r.id=rtrc.recipe_id JOIN recipe_category rc ON rtrc.recipe_category_id = rc" +
                    ".id " +
                    "WHERE rc.id =?1"
    )
    Page<Recipe> findRecipesByCategoryId(long categoryId, Pageable pageable);

    int countByAuthor_Id(long id);

    @Query(value = """
            SELECT sum(r.rating) FROM Recipe r
            WHERE r.author.username = ?1
    """)
    int sumRatingsOfUserRecipes(String username);
    
    @Query(value = """
            SELECT DISTINCT r FROM Recipe r
            WHERE LOWER(r.name) LIKE %?1% AND (?2 = r.category OR ?2 IS NULL)
    """)
    Page<Recipe> findByQueryAndCategory(String query, RecipeCategory category, PageRequest pageReq);
    
    Optional<Recipe> findFirstByAuthor_UsernameOrderByRating(String username);
    
    int countRecipesByAuthor_Username(String username);

    @Query(value = """
        SELECT r.ingredients FROM Recipe r
    """)
    List<IngredientWithQuantity> findAllAvailableIngredients();
    
}