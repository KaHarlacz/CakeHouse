package kharlacz.springapp.recipe;

import kharlacz.springapp.recipe.category.CategoryDtoMapper;
import kharlacz.springapp.recipe.comment.CommentDtoMapper;
import kharlacz.springapp.recipe.ingredient.IngredientDtoMapper;

import java.util.stream.Collectors;

public class RecipeEntryDetailsMapper {

    static RecipeEntryDetails map(Recipe recipe) {
        final var ingredients = recipe.getIngredients()
                .stream()
                .map(IngredientDtoMapper::map)
                .collect(Collectors.toSet());
        
        final var categories = recipe.getCategories()
                .stream()
                .map(CategoryDtoMapper::map)
                .collect(Collectors.toSet());
        
        final var comments = recipe.getComments()
                .stream()
                .map(CommentDtoMapper::map)
                .collect(Collectors.toSet());

        return RecipeEntryDetails.builder()
                .id(recipe.getId())
                .author(recipe.getAuthor().getUsername())
                .desc(recipe.getDesc())
                .dateAdded(recipe.getDateAdded())
                .imageB64(recipe.getImageB64())
                .name(recipe.getName())
                .prepMethod(recipe.getPrepMethod())
                .rating(recipe.getRating())
                .ingredients(ingredients)
                .categories(categories)
                .comments(comments)
                .build();
    }
}
