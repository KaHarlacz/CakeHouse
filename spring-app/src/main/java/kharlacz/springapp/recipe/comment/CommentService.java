package kharlacz.springapp.recipe.comment;

import kharlacz.springapp.recipe.Recipe;
import kharlacz.springapp.recipe.RecipeRepo;
import kharlacz.springapp.recipe.comment.post.CommentPost;
import kharlacz.springapp.recipe.comment.post.CommentPostResponse;
import kharlacz.springapp.user.authentication.BasicAuthString;
import kharlacz.springapp.user.User;
import kharlacz.springapp.user.UserRepo;
import kharlacz.springapp.user.ban.BanService;
import kharlacz.springapp.user.notification.NotificationService;
import kharlacz.springapp.util.content.filter.ContentFilterService;
import kharlacz.springapp.util.content.filter.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private final ContentFilterService contentFilterService;
    private final NotificationService notificationService;
    private final BanService banService;
    private final CommentRepo commentRepo;
    private final RecipeRepo recipeRepo;
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

    public CommentPostResponse addComment(CommentPost comment, BasicAuthString creditentials) {
        final var reservationsToComment =
                contentFilterService.filterContentBeingSaved(comment.getContent());
        final var isSafeComment = reservationsToComment.isEmpty();

        if (!isSafeComment) {
            banUser(creditentials.getUsername(), reservationsToComment);
            return CommentPostResponse.fail("Inappropriate content detected. You were banned.");
        }
        addCommentToDb(comment, creditentials.getUsername());
        notifyRecipeAuthor(comment.getRecipeId());
        return CommentPostResponse.success("Comment successfully added.");
    }

    private void banUser(String username, List<Reservation> reservationsToComment) {
        banService.banUser(username, reservationsToComment);
    }

    private void addCommentToDb(CommentPost comment, String author) {
        final var user = getUserByUsername(author);
        final var recipe = getRecipeById(comment.getRecipeId());

        commentRepo.save(Comment.builder()
                .author(user)
                .targetRecipe(recipe)
                .dateAdded(new Date())
                .content(comment.getContent())
                .build());
    }

    private User getUserByUsername(String username) {
        return userRepo
                .findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException("Comment must be linked to existing user.")
                );
    }

    private Recipe getRecipeById(long recipeId) {
        return recipeRepo
                .findById(recipeId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Comment target recipe must exist.")
                );
    }

    private void notifyRecipeAuthor(long recipeId) {
        final var recipe = recipeRepo.findById(recipeId);
        final var user = recipe.orElseThrow()
                .getAuthor();
        notificationService.notifyRecipeWasCommented(user);
    }
}
