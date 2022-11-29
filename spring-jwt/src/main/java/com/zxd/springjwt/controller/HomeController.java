package com.zxd.springjwt.controller;

import com.zxd.springjwt.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = "Projects")
public class HomeController {
    @Autowired
    private AuthService authService;


    @ApiOperation(value = "Get All Projects")
    @PostMapping("/login")
    public String login(@ApiParam("用户名") String username, @ApiParam("密码") String password) {
        String t =  authService.login( username, password );
        log.info("t is: "+t);
        return t;
    }

    @GetMapping("/test")
    @ApiOperation(value = "test method")
    public String test() {
        return "test";
    }
}
