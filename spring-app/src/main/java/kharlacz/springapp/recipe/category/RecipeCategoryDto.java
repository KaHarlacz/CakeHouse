package kharlacz.springapp.recipe.category;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class RecipeCategoryDto {
    private Long id;
    private String name;
}
