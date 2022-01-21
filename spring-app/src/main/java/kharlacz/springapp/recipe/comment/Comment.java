package kharlacz.springapp.recipe.comment;

import kharlacz.springapp.recipe.Recipe;
import kharlacz.springapp.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "date_added")
    private Date dateAdded;
    
    private String content;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe targetRecipe;
}
