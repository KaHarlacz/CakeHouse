package kharlacz.springapp.user;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserStats {
    private String username;
    private String email;
    private String bestRecipe;
    private int recipesAdded;
    private int commentsAdded;
    private int userRating;
}
