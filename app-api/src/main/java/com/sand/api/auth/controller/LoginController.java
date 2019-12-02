package com.sand.api.auth.controller;

import com.sand.api.annotation.CheckToken;
import com.sand.biz.system.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description
 * @author: YunLong
 * @create: 2019-11-23
 **/
@RequestMapping("/auth")
@RestController
public class LoginController {

    @Autowired
    private AuthService authService;

    public String register(@RequestParam("mobile") String mobile,
                           @RequestParam("password") String password){
        return authService.register(mobile, password);
    }

    @PostMapping("/login")
    public String login(@RequestParam("mobile") String mobile,
                        @RequestParam("password") String password){
        return authService.login(mobile, password);
    }

    @CheckToken
    @GetMapping("/token/check")
    public String checkWithToken(){
        return "Check token OK!";
    }

}
