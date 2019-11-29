package com.sand.biz.system;

import com.sand.biz.config.CacheConfig;
import com.sand.biz.exception.LoginException;
import com.sand.biz.utils.JwtUtils;
import com.sand.biz.utils.MD5Util;
import com.sand.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {

    private Cache tokenCache;

    @Autowired
    private UserService userService;
    @Autowired
    private CacheManager cacheManager;

    public void init(){
        tokenCache = cacheManager.getCache(CacheConfig.TOKEN_CACHE);
    }

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
            throw new LoginException("user not exist");
        }
        if(!checkPassword(password, user)){
            throw new LoginException("password error.");
        }
        String token = JwtUtils.generateJWT(user.getUserId().toString());
        tokenCache.put(token, user);
        return token;
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

    public boolean checkPassword(String password, User user){
        String text = user.getSalt() + password;
        return MD5Util.encrypt(text).equals(user.getPassword());
    }
}
