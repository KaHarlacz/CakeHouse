package kharlacz.springapp.user.authentication;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AuthResponse {
    private Long userId;

    public static AuthResponse of(Long userId) {
        return new AuthResponse(userId);
    }
}
