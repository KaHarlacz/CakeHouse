package kharlacz.springapp.recipe;

import kharlacz.springapp.recipe.comment.CommentDtoMapper;
import kharlacz.springapp.recipe.ingredient.IngredientQuantityDto;
import kharlacz.springapp.recipe.services.dto.RecipeDetails;

import java.util.Set;
import java.util.stream.Collectors;

public class RecipeEntryDetailsMapper {

    public static RecipeDetails map(Recipe recipe) {
        final var ingredients = recipe.getIngredients()
                .stream()
                .map(IngredientQuantityDto::from)
                .collect(Collectors.toSet());

        final var comments = recipe.getComments()
                .stream()
                .map(CommentDtoMapper::map)
                .collect(Collectors.toSet());

        return RecipeDetails.builder()
                .id(recipe.getId())
                .author(recipe.getAuthor().getUsername())
                .desc(recipe.getDesc())
                .dateAdded(recipe.getDateAdded())
                .imageB64(recipe.getImageB64())
                .name(recipe.getName())
                .prepMethod(recipe.getPrepMethod())
                .rating(recipe.getRating())
                .ingredients(ingredients)
                .categories(Set.of(recipe.getCategory()))
                .comments(comments)
                .build();
    }
}
