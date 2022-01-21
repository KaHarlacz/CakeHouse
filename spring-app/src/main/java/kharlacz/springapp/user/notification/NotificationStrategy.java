package kharlacz.springapp.user.notification;

import kharlacz.springapp.user.User;

public interface NotificationStrategy {
    void notify(User target, String message);
}
