package kharlacz.springapp.user.notification;

import kharlacz.springapp.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Component
public class EmailNotification implements NotificationStrategy {

    @Value("${notification.email}")
    private String emailAddress;

    @Value("${notification.password}")
    private String password;

    @Override
    public void notify(User target, String message) {
        new Thread(() -> {
            try {
                final var email = new SimpleEmail();
                email.setHostName("poczta.interia.pl");
                email.setSmtpPort(465);
                email.setAuthenticator(new DefaultAuthenticator(emailAddress, password));
                email.setSSLOnConnect(true);
                email.setFrom(emailAddress);
                email.setSubject("Someone has commented your recipe!");
                email.setMsg(message);
                email.addTo(target.getEmail());
                email.send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
