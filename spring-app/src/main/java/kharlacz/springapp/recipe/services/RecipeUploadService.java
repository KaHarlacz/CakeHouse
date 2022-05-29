package kharlacz.springapp.recipe.services;

import kharlacz.springapp.recipe.Recipe;
import kharlacz.springapp.recipe.RecipeRepo;
import kharlacz.springapp.recipe.services.commands.UploadRecipeCommand;
import kharlacz.springapp.user.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeUploadService {
    private final RecipeRepo recipeRepo;
    private final UserRepo userRepo;
    
    public void upload(UploadRecipeCommand uploadCmd) {
        // TODO: Throw more sensible exception
        var userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        var recipe = Recipe.from(uploadCmd);
        var author = userRepo.findByUsername(userDetails.getUsername())
                .orElseThrow();
        recipe.setAuthor(author);
        recipeRepo.save(recipe);
    }
}
