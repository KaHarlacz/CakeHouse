package kharlacz.springapp.recipe;

import kharlacz.springapp.recipe.category.RecipeCategory;
import kharlacz.springapp.recipe.comment.Comment;
import kharlacz.springapp.recipe.ingredient.Ingredient;
import kharlacz.springapp.recipe.ingredient.IngredientWithQuantity;
import kharlacz.springapp.recipe.ingredient.unit.Unit;
import kharlacz.springapp.recipe.services.commands.UploadRecipeCommand;
import kharlacz.springapp.user.User;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hibernate.annotations.CascadeType.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Enumerated(EnumType.STRING)
    RecipeCategory category;
    
    @Column(name = "description")
    private String desc;
    
    @Column(name = "prep_method")
    private String prepMethod;
    
    private Integer rating;
    
    @Column(name = "date_added")
    private Date dateAdded;
    
    @Lob
    @Column(name = "image")
    private String imageB64;

    @OneToMany(mappedBy = "recipe")
    @Cascade(PERSIST)
    private Set<IngredientWithQuantity> ingredients = new HashSet<>();

    @ManyToOne
    private User author;
    
    @OneToMany(mappedBy = "targetRecipe")
    private Set<Comment> comments = new HashSet<>();
    
    public static Recipe from(UploadRecipeCommand recipeCmd) {
        return Recipe.builder()
                .name(recipeCmd.name())
                .category(recipeCmd.category())
                .desc(recipeCmd.desc())
                .prepMethod(recipeCmd.prepMethod())
                .rating(0)
                .dateAdded(new Date())
                .imageB64(recipeCmd.imageB64())
                .build();
    }
}
