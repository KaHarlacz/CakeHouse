package kharlacz.springapp.recipe.category;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RecipeCategory {
    DINNER("dinner"),
    THANKSGIVING("thanksgiving"),
    CHRISTMAS("christmas");
    
    private String name;
}
