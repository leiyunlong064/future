package com.sand.mgt.system;

import com.sand.biz.system.UserService;
import com.sand.common.utils.ResponseEntity;
import com.sand.mgt.auth.AuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity createUser(String name,
                                     String password,
                                     String mobile) {
        Long userId = userService.createUser(mobile, password, name);
        return ResponseEntity.ok(userId.toString());
    }
}
