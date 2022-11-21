package taco.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Slf4j
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity(debug = true)
// spring security 5.7-M2 建议使用 SecurityFilterChain 替代 WebSecurityConfigurerAdapter
// @see https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 内存用户配置
//        auth.inMemoryAuthentication()
//                .withUser("zxd")
//                   .password("zxd")
//                   .authorities("ROLE_USER")
//                .and()
//                .withUser("woody")
//                .password("woody")
//                .authorities("ROLE_USER");
        http.authorizeHttpRequests()
                // @see https://github.com/habuma/spring-in-action-5-samples/issues/74 must remove ROLE_
                // https://docs.spring.io/spring-security/reference/5.8/reactive/authorization/method.html#jc-enable-reactive-method-security-authorization-manager
                // Also, for role-based authorization, Spring Security adds a default ROLE_ prefix, which is uses when evaluating expressions like hasRole
//                .antMatchers("/design","/orders/**").hasRole("USER")
//                .antMatchers("/design","/orders/**", "/orders").hasAuthority("ROLE_USER")
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/design/recent","/orders/**", "/orders").permitAll()
//                .antMatchers("/", "/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/ingredients").permitAll()
                .antMatchers("/**").permitAll()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/design")
                .and().logout().logoutSuccessUrl("/")
                .and().csrf().ignoringAntMatchers("/h2-console/**")

                // Allow pages to be loaded in frames from the same origin; needed for H2-Console
                // tag::frameOptionsSameOrigin[]
                .and()
                .headers()
                .frameOptions()
                .sameOrigin();

        // 禁用 csrf，不推荐
//        .and()
//                .csrf()
//                .disable()
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }
}
