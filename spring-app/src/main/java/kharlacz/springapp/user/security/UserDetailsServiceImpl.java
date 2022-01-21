package kharlacz.springapp.user.security;

import kharlacz.springapp.user.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    private static UserDetailsServiceImpl instance;
    
    private final UserRepo userRepo;

    public static UserDetailsServiceImpl getInstance(UserRepo userRepo) {
        if (instance == null) {
            instance = new UserDetailsServiceImpl(userRepo);
        }
        return instance;
    }

    private UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userRepo.findByUsername(username);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with specified username not found.");
        }
        return new UserDetailsAdapter(user.get());
    }
}
