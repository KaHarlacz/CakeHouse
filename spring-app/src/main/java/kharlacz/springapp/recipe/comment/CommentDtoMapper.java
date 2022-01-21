package kharlacz.springapp.recipe.comment;

public class CommentDtoMapper {
    
    public static CommentDto map(Comment comment) {
        return CommentDto.builder()
                .author(comment.getAuthor().getUsername())
                .content(comment.getContent())
                .dateAdded(comment.getDateAdded())
                .build();
    }
}
