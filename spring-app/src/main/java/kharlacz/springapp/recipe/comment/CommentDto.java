package kharlacz.springapp.recipe.comment;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class CommentDto {
    private String author;
    private String content;
    private Date dateAdded;
}
