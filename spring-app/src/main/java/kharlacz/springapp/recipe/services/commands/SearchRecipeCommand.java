package kharlacz.springapp.recipe.services.commands;


import io.soabase.recordbuilder.core.RecordBuilder;
import kharlacz.springapp.recipe.SearchRecipeReqBuilder;

@RecordBuilder
public record SearchRecipeCommand(String query, String category, int page, int size, String sortBy)
    implements SearchRecipeCommandBuilder.With {

    public SearchRecipeCommand {
        if (query == null) {
            query = "";
        }
        if (category == null) {
            category = "";
        }
        if (sortBy == null) {
            sortBy = "";
        }
    }
}
