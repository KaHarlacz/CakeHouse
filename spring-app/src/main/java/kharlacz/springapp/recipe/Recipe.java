package kharlacz.springapp.recipe;

import kharlacz.springapp.recipe.category.RecipeCategory;
import kharlacz.springapp.recipe.comment.Comment;
import kharlacz.springapp.recipe.ingredient.IngredientWithQuantity;
import kharlacz.springapp.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Set<IngredientWithQuantity> ingredients = new HashSet<>();

    @ManyToOne
    private User author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_to_recipe_category",
            joinColumns = @JoinColumn(name = "recipe_category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    )
    private Set<RecipeCategory> categories;
    
    @OneToMany(mappedBy = "targetRecipe")
    private Set<Comment> comments;
}
