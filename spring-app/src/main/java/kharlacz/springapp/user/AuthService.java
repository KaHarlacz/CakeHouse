package kharlacz.springapp.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kharlacz.springapp.exception.AuthException;
import kharlacz.springapp.user.authentication.AuthReq;
import kharlacz.springapp.user.authentication.AuthRes;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class AuthService {

    public static final int TOKEN_EXP_TIME = 900000;
    private final UserRepo userRepo;

    public AuthRes login(AuthReq authReq) {
        var user = userRepo
                .findByUsernameAndPassword(authReq.username(), authReq.password())
                .orElseThrow(() -> new AuthException("Provided password doesn't match username"));

        var jwtToken = generateJwtToken(user.getUsername());
        return new AuthRes(jwtToken, new Date(System.currentTimeMillis() + TOKEN_EXP_TIME));
    }

    private String generateJwtToken(String username) {
        // TODO: Get secret key from env variable
        var secretKey = "B^K@jN^cg^@FCTBM%_aNSez2Lq9%qqag$pk%t*42XKB8DGPWnD=Ms6RW&6eHwU#?";
        var grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("USER").stream()
                .map(GrantedAuthority::getAuthority).toList();

        // TODO: Find out how to change authorities to roles
        // TODO: Store user roles in database
        // TODO: Keep user logged in if user keeps sending requests
        var token = Jwts.builder()
                .setSubject(username)
                .claim("authorities", grantedAuthorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP_TIME))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
