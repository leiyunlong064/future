package com.future.api.auth.controller;

import com.future.api.annotation.CheckToken;
import com.future.api.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Map<String, String> userMap = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostConstruct
    private void init(){
        userMap.put("admin","123456");
        userMap.put("jason", "123456");
    }

    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password){
        if(!userMap.get(userName).equals(password)){
            throw new RuntimeException("UNAUTHORIZED");
        }

        return JwtUtils.generateJWT("121212",userName);
    }

    @CheckToken
    @GetMapping("/token/check")
    public String checkWithToken(){
        return "Check token OK!";
    }

}
