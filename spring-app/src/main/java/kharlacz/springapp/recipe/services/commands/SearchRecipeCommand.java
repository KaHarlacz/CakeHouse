package kharlacz.springapp.recipe.services.commands;


import io.soabase.recordbuilder.core.RecordBuilder;
import kharlacz.springapp.recipe.category.RecipeCategory;

@RecordBuilder
public record SearchRecipeCommand(
        String query,
        RecipeCategory category,
        int page,
        int size,
        String sortBy) implements SearchRecipeCommandBuilder.With {

    public SearchRecipeCommand {
        if (query == null) {
            query = "";
        }
        if (sortBy == null) {
            sortBy = "";
        }
    }
}
