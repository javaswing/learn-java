package com.zxd.springsecurity.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        log.info(String.valueOf(principal));
        return "home";
    }
}
