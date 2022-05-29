package kharlacz.springapp.api;

import kharlacz.springapp.user.UserStats;
import kharlacz.springapp.user.UserStatsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserStatsService statsService;
    
    @GetMapping("/users/{username}/stats")
    public UserStats getUserStats(
            @PathVariable("username") String username
    ) {
        return statsService.getUserStats(username);
    }
}
