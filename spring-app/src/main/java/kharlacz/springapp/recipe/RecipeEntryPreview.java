package kharlacz.springapp.recipe;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class RecipeEntryPreview {
    private Long id;
    private String name;
    private String desc;
    private String author;
    private Integer rating;
    private Date dateAdded;
    private String image;
}

