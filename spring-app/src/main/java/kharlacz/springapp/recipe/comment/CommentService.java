package kharlacz.springapp.recipe.comment;

import kharlacz.springapp.recipe.Recipe;
import kharlacz.springapp.recipe.comment.post.CommentReq;
import kharlacz.springapp.recipe.comment.post.CommentRes;
import kharlacz.springapp.user.UserRepo;
import kharlacz.springapp.util.content.filter.ContentFilterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private final ContentFilterService contentFilterService;
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;

    public CommentDto getComment(long id) {
        return commentRepo.findById(id)
                .map(comment -> CommentDto.builder()
                        .author(comment.getAuthor().getUsername())
                        .content(comment.getContent())
                        .dateAdded(comment.getDateAdded())
                        .build())
                .orElse(new CommentDto());
    }

    public List<CommentDto> getCommentsForRecipe(long recipeId) {
        return commentRepo.findByTargetRecipe_Id(recipeId)
                .stream()
                .map(CommentDtoMapper::map)
                .collect(Collectors.toList());
    }

    public CommentRes addComment(long recipeId, CommentReq commentReq) {
        final var reservationsToComment =
                contentFilterService.filterContentBeingSaved(commentReq.comment());
        final var isSafeComment = reservationsToComment.isEmpty();

        if (!isSafeComment) {
            return CommentRes.fail("Cannot add comment with bad language or html tags");
        }
        
        // TODO: Throw sensible exception
        var author = userRepo.findByUsername(commentReq.username())
                        .orElseThrow();
        
        commentRepo.save(Comment.builder()
                .author(author)
                .content(commentReq.comment())
                .dateAdded(new Date())
                .targetRecipe(Recipe.builder().id(recipeId).build())
                .build());
        return CommentRes.success("Comment successfully added.");
    }
}
