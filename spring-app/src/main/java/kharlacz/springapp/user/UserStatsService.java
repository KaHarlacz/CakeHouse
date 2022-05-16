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

    public UserStats getProfileDetails(long userId) {
        var user = userRepo.findById(userId).orElseThrow();
        var recipesCount = recipeRepo.countByAuthor_Id(userId);
        var commentCount = commentRepo.countByAuthor_Id(userId);
        var userRating = recipeRepo.sumRatingOfUserRecipes(userId);
        var bestRecipe = recipeRepo.findFirstByAuthor_IdOrderByRatingDesc(userId)
                .map(Recipe::getName).orElseThrow();
        return UserStats.builder().commentsAdded(commentCount).recipesAdded(recipesCount)
                .bestRecipe(bestRecipe).userRating(userRating).username(user.getUsername())
                .email(user.getEmail()).build();
    }
}
