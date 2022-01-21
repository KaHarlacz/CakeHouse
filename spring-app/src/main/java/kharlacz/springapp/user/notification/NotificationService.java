package kharlacz.springapp.user.notification;

import kharlacz.springapp.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationService {

    private final NotificationStrategy notification;

    public void notifyRecipeWasCommented(User user) {
        final var message = """
                Hi!
                
                We would like to inform you that your recipes are getting
                deserved publicity. One of them has just been commented.
                
                Best regards,
                CakeHouse team
                """;
        notification.notify(user, message);
    }
}
