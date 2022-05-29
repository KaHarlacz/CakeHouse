package kharlacz.springapp.api;

import kharlacz.springapp.user.AuthService;
import kharlacz.springapp.user.authentication.AuthReq;
import kharlacz.springapp.user.authentication.AuthRes;
import kharlacz.springapp.user.ban.BanService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    AuthRes login(@RequestBody AuthReq authReq) {
        return authService.login(authReq);
    }
}
