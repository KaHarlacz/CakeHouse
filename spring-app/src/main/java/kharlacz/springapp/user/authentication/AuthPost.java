package kharlacz.springapp.user.authentication;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AuthPost {
    private String username;
    private String password;
}
