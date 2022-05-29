package kharlacz.springapp.api;

import io.jsonwebtoken.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    // TODO: Secret key should be taken from env variable
    private final String SECRET_KEY = "B^K@jN^cg^@FCTBM%_aNSez2Lq9%qqag$pk%t*42XKB8DGPWnD=Ms6RW&6eHwU#?";

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest req,
            @NotNull HttpServletResponse res,
            @NotNull FilterChain chain) throws ServletException, IOException {
        try {
            if (checkJwtTokenPresent(req)) {
                Claims claims = validateToken(req);
                if (claims.get("authorities") != null) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(req, res);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            res.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    private boolean checkJwtTokenPresent(HttpServletRequest req) {
        var authHeader = req.getHeader(HEADER);
        return authHeader != null && authHeader.startsWith(PREFIX);
    }

    private Claims validateToken(HttpServletRequest req) {
        var jwtToken = req.getHeader(HEADER).replace(PREFIX, "");
        return Jwts.parser().setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(jwtToken).getBody();
    }

    private void setUpSpringAuthentication(Claims claims) {
        List<String> authorities = (List) claims.get("authorities");
        var grantedAuthorities = authorities.stream()
                .map(SimpleGrantedAuthority::new).toList();

        var auth = new UsernamePasswordAuthenticationToken(
                claims.getSubject(), null, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
