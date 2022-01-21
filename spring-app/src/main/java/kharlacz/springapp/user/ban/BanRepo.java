package kharlacz.springapp.user.ban;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BanRepo extends JpaRepository<Ban, Long> {
    Optional<Ban> findBanByUser_Id(long userId);
}
