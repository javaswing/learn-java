package tacos.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity(debug = true)
// spring security 5.7-M2 建议使用 SecurityFilterChain 替代 WebSecurityConfigurerAdapter
// @see https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepositoryUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll() // needed for Angular/CORS
                .antMatchers("/design", "/orders/**").permitAll()
                //.access("hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.PATCH, "/ingredients").permitAll().antMatchers("/**").access("permitAll")

                .and().formLogin().loginPage("/login")

                .and().httpBasic().realmName("Taco Cloud")

                .and().logout().logoutSuccessUrl("/")

                .and().csrf().ignoringAntMatchers("/h2-console/**", "/ingredients/**", "/design", "/orders/**")

                // Allow pages to be loaded in frames from the same origin; needed for H2-Console
                .and().headers().frameOptions().sameOrigin();
    }

    @Bean
    public PasswordEncoder encoder() {
//    return new StandardPasswordEncoder("53cr3t");
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());

    }
}