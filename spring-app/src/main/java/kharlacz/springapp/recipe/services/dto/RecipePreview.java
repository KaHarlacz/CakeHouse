package kharlacz.springapp.recipe.services.dto;

import io.soabase.recordbuilder.core.RecordBuilder;
import kharlacz.springapp.recipe.Recipe;
import kharlacz.springapp.recipe.services.dto.RecipePreviewBuilder;

import java.util.Date;

@RecordBuilder
public record RecipePreview (
        Long id,
        String name,
        String desc,
        String author,
        Integer rating,
        Date dateAdded,
        String imageB64
) {
    public static RecipePreview from(Recipe recipe) {
        return RecipePreviewBuilder.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .desc(recipe.getDesc())
                .author(recipe.getAuthor().getUsername())
                .rating(recipe.getRating())
                .imageB64(recipe.getImageB64())
                .dateAdded(recipe.getDateAdded())
                .build();
    }
}

