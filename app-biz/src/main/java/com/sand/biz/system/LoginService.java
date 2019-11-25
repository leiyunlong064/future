package com.sand.biz.system;

import com.sand.biz.utils.JwtUtils;
import com.sand.biz.utils.MD5Util;
import com.sand.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

@Service
public class LoginService {

    private static final String USER_PASSWORD_SECRET = "aewedsdfwefesmkxcgy";

    @Autowired
    private UserService userService;

    public synchronized String register(String mobile, String password){
        User user = userService.getUserByMobile(mobile);
        if(user != null){
            throw new RuntimeException("The mobile has already been registered!");
        }
        String salt = generateSalt(mobile);
        String hash = generateHash(salt, password);
        user = new User()
                .setMobile(mobile)
                .setPassword(hash)
                .setSalt(salt);
        userService.create(user);
        return JwtUtils.generateJWT(user.getUserId().toString());
    }

    public String login(String mobile, String password){
        User user = userService.getUserByMobile(mobile);
        if(user == null){
            throw new RuntimeException("user not exist");
        }
        if(!checkPassword(password, user)){
            throw new RuntimeException("password error.");
        }
        return JwtUtils.generateJWT(user.getUserId().toString());
    }

    private String generateSalt(String username) {
        Date now = new Date();
        String text = now.toInstant().toString() + username;
        return MD5Util.encrypt(text);
    }

    private String generateHash(String salt,String password) {
        String text = salt + password;
        return MD5Util.encrypt(text);
    }

    private boolean checkPassword(String password, User user){
        String text = user.getSalt() + password;
        return MD5Util.encrypt(text).equals(user.getPassword());
    }
}
