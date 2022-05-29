package kharlacz.springapp.recipe.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    
    List<Comment> findByTargetRecipe_Id(long recipeId);
    
    int countByAuthor_Id(long id);
    
    int countCommentsByAuthor_Username(String username);
}
