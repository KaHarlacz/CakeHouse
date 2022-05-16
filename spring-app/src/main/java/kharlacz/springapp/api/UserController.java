package kharlacz.springapp.api;

import kharlacz.springapp.user.authentication.BasicAuthString;
import kharlacz.springapp.user.authentication.AuthPost;
import kharlacz.springapp.user.UserAuthService;
import kharlacz.springapp.user.authentication.AuthResponse;
import kharlacz.springapp.user.ban.BanService;
import kharlacz.springapp.user.ban.BanDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserAuthService userService;
    private final BanService banService;

    @PostMapping("/user")
    @ResponseBody 
    AuthResponse authenticate(@RequestBody AuthPost authPost) {
        return userService.login(authPost);
    }
    
    @GetMapping("/user/ban")
    @ResponseBody
    BanDto getUserBan(@RequestHeader(name = "authorization") BasicAuthString creditentials) {    
        return banService.getBanForUser(creditentials.getUsername());
    }
}
