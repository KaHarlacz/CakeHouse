package kharlacz.springapp.user;

import kharlacz.springapp.user.authentication.AuthPost;
import kharlacz.springapp.user.authentication.AuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public AuthResponse authenticate(AuthPost authPost) {
        // TODO: optimize
        final var user = userRepo
                .findByUsernameAndPassword(authPost.getUsername(), authPost.getPassword());
        if (user.isPresent()) {
            final var userId = userRepo.findUserIdByUsername(authPost.getUsername());
            return AuthResponse.of(userId);
        }
        return AuthResponse.of(null);
    }
}
