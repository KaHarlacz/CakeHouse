package kharlacz.springapp.user.notification;

import kharlacz.springapp.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

import static java.lang.Integer.parseInt;

@AllArgsConstructor
@Component
public class EmailNotification implements NotificationStrategy {
    
    @Override
    public void notify(User target, String message) {
        new Thread(() -> sendEmailTask(target, message)).start();
    }
    
    private void sendEmailTask(User target, String message) {
        try {
            final var envVars = System.getenv();
            final var email = new SimpleEmail();
            final var auth = new DefaultAuthenticator(
                    envVars.get("CAKEHOUSE_EMAIL_ADDRESS"),
                    envVars.get("CAKEHOUSE_EMAIL_PASSWORD"));
            
            email.setHostName(envVars.get("CAKEHOUSE_EMAIL_HOST"));
            email.setSmtpPort(parseInt(envVars.get("CAKEHOUSE_EMAIL_SMTP_PORT")));
            email.setAuthenticator(auth);
            email.setSSLOnConnect(true);
            email.setFrom(envVars.get("CAKEHOUSE_EMAIL_ADDRESS"));
            email.setSubject("Someone has commented your recipe!");
            email.setMsg(message);
            email.addTo(target.getEmail());
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
