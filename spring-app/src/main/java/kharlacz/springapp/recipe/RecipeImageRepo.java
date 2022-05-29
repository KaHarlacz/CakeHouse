package kharlacz.springapp.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeImageRepo extends JpaRepository<Recipe, Long> {
    
    @Query("SELECT r.imageB64 FROM Recipe r WHERE r.id = ?1")
    Optional<String> findRecipeImage(long id);
}
