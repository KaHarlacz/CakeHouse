package kharlacz.springapp.api;

import kharlacz.springapp.user.authentication.BasicAuthString;
import kharlacz.springapp.user.authentication.AuthPost;
import kharlacz.springapp.user.UserService;
import kharlacz.springapp.user.authentication.AuthResponse;
import kharlacz.springapp.user.ban.BanService;
import kharlacz.springapp.user.ban.BanDto;
import kharlacz.springapp.user.notification.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final BanService banService;

    @PostMapping("/user")
    @ResponseBody 
    AuthResponse authenticate(@RequestBody AuthPost authPost) {
        return userService.authenticate(authPost);
    }
    
    @GetMapping("/user/ban")
    @ResponseBody
    BanDto getUserBan(@RequestHeader(name = "authorization") BasicAuthString creditentials) {    
        return banService.getBanForUser(creditentials.getUsername());
    }
}
