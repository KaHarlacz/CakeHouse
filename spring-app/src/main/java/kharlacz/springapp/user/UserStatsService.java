package kharlacz.springapp.user;

import kharlacz.springapp.recipe.Recipe;
import kharlacz.springapp.recipe.RecipeRepo;
import kharlacz.springapp.recipe.comment.CommentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserStatsService {

    private final UserRepo userRepo;
    private final RecipeRepo recipeRepo;
    private final CommentRepo commentRepo;

    public UserStats getUserStats(String username) {
        // TODO: Throw some sensible exceptions
        var user = userRepo.findByUsername(username).orElseThrow();
        var recipesCount = recipeRepo.countRecipesByAuthor_Username(username);
        var commentCount = commentRepo.countCommentsByAuthor_Username(username);
        var userRating = recipeRepo.sumRatingsOfUserRecipes(username);
        var bestRecipe = recipeRepo.findFirstByAuthor_UsernameOrderByRating(username)
                .map(Recipe::getName).orElseThrow();
        return UserStats.builder().commentsAdded(commentCount).recipesAdded(recipesCount)
                .bestRecipe(bestRecipe).userRating(userRating).username(user.getUsername())
                .email(user.getEmail()).build();
    }
}
