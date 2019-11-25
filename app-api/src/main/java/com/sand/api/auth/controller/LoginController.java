package com.sand.api.auth.controller;

import com.sand.api.annotation.CheckToken;
import com.sand.api.utils.JwtUtils;
import com.sand.biz.system.LoginService;
import com.sand.biz.system.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @author: YunLong
 * @create: 2019-11-23
 **/
@RequestMapping("/auth")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    public String register(@RequestParam("mobile") String mobile,
                           @RequestParam("password") String password){
        return loginService.register(mobile, password);
    }

    @PostMapping("/login")
    public String login(@RequestParam("mobile") String mobile,
                        @RequestParam("password") String password){
        return loginService.login(mobile, password);
    }

    @CheckToken
    @GetMapping("/token/check")
    public String checkWithToken(){
        return "Check token OK!";
    }

}
