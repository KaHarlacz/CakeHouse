package kharlacz.springapp.recipe.services;

import kharlacz.springapp.recipe.RecipeImageRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeImageService {
    
    private final RecipeImageRepo imageRepo;

    // TODO:
    public byte[] getRecipeImage(long recipeId) {
        //        var imageB64 = imageRepo.findRecipeImage(recipeId)
        //                .orElse("");
        //        return Base64.getDecoder().decode(imageB64);
        return null;
    }
}
