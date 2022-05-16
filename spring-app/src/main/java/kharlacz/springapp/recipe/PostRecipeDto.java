package kharlacz.springapp.recipe;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
@Getter
public class PostRecipeDto {
    private String name;
    private String desc;
    private String prep;
    private String imageB64;
    private List<String> ingredients;
    private List<String> categories;
}
