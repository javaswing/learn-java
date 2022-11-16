package com.zxd.springsecurity.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
// spring security 5.7-M2 建议使用 SecurityFilterChain 替代 WebSecurityConfigurerAdapter
// @see https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
        http.authorizeHttpRequests().antMatchers("/design", "/orders").hasRole("ROLE_USER")
                .antMatchers("/", "/**").permitAll()

                .and().formLogin().loginPage("/login").defaultSuccessUrl("/design")
                .and().logout().logoutSuccessUrl("/");

    }

}
