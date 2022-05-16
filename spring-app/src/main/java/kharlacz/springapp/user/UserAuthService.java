package kharlacz.springapp.user;

import kharlacz.springapp.user.authentication.AuthPost;
import kharlacz.springapp.user.authentication.AuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthService {

    private final UserRepo userRepo;

    public AuthResponse login(AuthPost authPost) {
        // TODO: optimize
        var user = userRepo
                .findByUsernameAndPassword(authPost.getUsername(), authPost.getPassword());
        if (user.isPresent()) {
            var userId = userRepo.findUserIdByUsername(authPost.getUsername());
            return AuthResponse.of(userId);
        }
        return AuthResponse.of(null);
    }
}
