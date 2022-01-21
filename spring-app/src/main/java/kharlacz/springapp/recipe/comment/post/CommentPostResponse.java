package kharlacz.springapp.recipe.comment.post;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CommentPostResponse {
    private String message;
    private boolean success = false;
    
    public static CommentPostResponse success(String message) {
        return new CommentPostResponse(message, true);
    }

    public static CommentPostResponse fail(String message) {
        return new CommentPostResponse(message, false);
    }
}
