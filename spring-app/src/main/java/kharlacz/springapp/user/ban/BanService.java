package kharlacz.springapp.user.ban;

import kharlacz.springapp.user.UserRepo;
import kharlacz.springapp.util.content.filter.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BanService {

    private final UserRepo userRepo;
    private final BanRepo banRepo;

    public BanDto getBanForUser(String username) {
        final var userId = userRepo.findUserIdByUsername(username);
        if (userId == null) {
            return BanDto.empty();
        }
        final var ban = banRepo.findBanByUser_Id(userId);
        return ban.isPresent() ? BanDtoMapper.map(ban.get()) : BanDto.empty();
    }

    public void banUser(String username, List<Reservation> reservations) {
        final var cause = formBanCause(reservations);
        final var banHours = summarizeBanDuration(reservations);
        final var user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Cannot ban not existing user."));
        final var ban = Ban.of(user, cause, banHours);
        banRepo.save(ban);
    }

    private String formBanCause(List<Reservation> reservations) {
        final var causeBuilder = new StringBuilder();
        reservations.stream()
                .map(Reservation::getMessage)
                .forEach(msg -> causeBuilder.append(msg).append("\n"));
        return causeBuilder.toString();
    }

    private int summarizeBanDuration(List<Reservation> reservations) {
        return reservations.stream()
                .map(Reservation::getPenaltyHours)
                .reduce(0, Integer::sum);
    }
}
