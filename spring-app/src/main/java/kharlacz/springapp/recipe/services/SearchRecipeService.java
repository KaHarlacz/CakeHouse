package kharlacz.springapp.recipe.services;

import kharlacz.springapp.recipe.RecipeRepo;
import kharlacz.springapp.recipe.services.dto.RecipePreview;
import kharlacz.springapp.recipe.services.commands.SearchRecipeCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@AllArgsConstructor
public class SearchRecipeService {

    private RecipeRepo recipeRepo;

    public Page<RecipePreview> search(SearchRecipeCommand searchCmd) {
        if (searchCmd.sortBy().equals("")) {
            searchCmd = searchCmd.with()
                    .sortBy("dateAdded").build();
        }
        
        var pageReq = PageRequest.of(
                searchCmd.page(), searchCmd.size(), DESC, searchCmd.sortBy());
        
        return recipeRepo.findByQueryAndCategory(
                searchCmd.query().toLowerCase(Locale.ROOT),
                searchCmd.category(),
                pageReq).map(RecipePreview::from);
    }

}
