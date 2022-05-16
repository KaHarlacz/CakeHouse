package kharlacz.springapp;

import kharlacz.springapp.user.UserRepo;
import kharlacz.springapp.user.security.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@AllArgsConstructor
@Setter
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationContext context;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        final var userDetailsService = context.getBean(UserDetailsServiceImpl.class);
        auth.userDetailsService(userDetailsService);
    }

    // Secure the endpoints with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/comment").hasRole("USER")
                .antMatchers("/**").permitAll().anyRequest().authenticated()
                .and()
                // TODO: Implement protection against CSRF
                .csrf().disable()
                .httpBasic();
        http.cors();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        final var userRepo = context.getBean(UserRepo.class);
        return UserDetailsServiceImpl.getInstance(userRepo);
    }
}
