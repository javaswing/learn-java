package com.zxd.springjwt.controller;

import com.zxd.springjwt.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class HomeController {
    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public String login(String username, String password) {
        String t =  authService.login( username, password );
        log.info("t is: "+t);
        return t;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
