package kharlacz.springapp.recipe;

import org.springframework.stereotype.Service;

@Service
class RecipeEntryPreviewMapper {
    
    static RecipeEntryPreview map(Recipe recipe) {
        return RecipeEntryPreview.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .desc(recipe.getDesc())
                .author(recipe.getAuthor().getUsername())
                .rating(recipe.getRating())
                .dateAdded(recipe.getDateAdded())
                .image(recipe.getImageB64())
                .build();
    }    
}
