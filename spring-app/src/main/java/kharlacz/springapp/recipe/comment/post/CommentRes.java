package kharlacz.springapp.recipe.comment.post;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CommentRes {
    private String message;
    private boolean success = false;
    
    public static CommentRes success(String message) {
        return new CommentRes(message, true);
    }

    public static CommentRes fail(String message) {
        return new CommentRes(message, false);
    }
}
