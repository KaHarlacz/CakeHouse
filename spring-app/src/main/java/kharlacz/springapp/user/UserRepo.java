package kharlacz.springapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    
    // TODO: find out if it is better way to do this
    @Query(nativeQuery = true, value = "SELECT id FROM user WHERE username = ?1")
    Long findUserIdByUsername(String username);
    
    Optional<User> findByUsernameAndPassword(String username, String password);
}
