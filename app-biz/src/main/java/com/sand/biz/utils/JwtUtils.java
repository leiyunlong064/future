package com.sand.biz.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @description
 * @author: YunLong
 * @create: 2019-11-21
 **/
public class JwtUtils {
    private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * 生成JWT字符串
     * @param userId
     * @return
     */
    public static String generateJWT(String userId){
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + JwtConstants.EXPIRE_TIME);

        return JWT
                .create()
                .withAudience(userId)
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(JwtConstants.SECRET_KEY));
    }

    public static boolean checkJWT(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JwtConstants.SECRET_KEY)).build();
        try {
            jwtVerifier.verify(token);
        }
        catch (JWTVerificationException ex){
            return false;
        }
        return true;
    }

}
