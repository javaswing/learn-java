package com.zxd.springjwt.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @PostMapping("/login")
    public String login(String username, String password) {
        return username + ", " + password;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
