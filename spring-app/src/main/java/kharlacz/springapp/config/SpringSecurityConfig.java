package kharlacz.springapp.config;

import kharlacz.springapp.api.JwtAuthFilter;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@Setter
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .addFilterAfter(new JwtAuthFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .antMatchers("/recipes/*").permitAll()
                .antMatchers("/recipes/*/comment").hasAuthority("USER")
                .antMatchers("*/upload").hasAuthority("USER")
                .antMatchers("/users/*").hasAuthority("USER")
                .anyRequest().authenticated();
    }
}
