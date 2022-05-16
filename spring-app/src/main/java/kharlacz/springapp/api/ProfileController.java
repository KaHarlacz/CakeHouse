package kharlacz.springapp.api;

import kharlacz.springapp.user.UserStats;
import kharlacz.springapp.user.UserStatsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProfileController {

    private final UserStatsService userDetailsService;

    @GetMapping("/users/{id}/stats")
    UserStats getProfileDetails(@PathVariable long id) {
        return userDetailsService.getProfileDetails(id);
    }

}
