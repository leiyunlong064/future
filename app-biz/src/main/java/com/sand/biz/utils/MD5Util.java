package com.sand.biz.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5Util {
    private static final String SALT = "1qazxsw2";

    private static final String ALGORITHM_NAME = "md5";

    private static final int HASH_ITERATIONS = 2;

    public static String encrypt(String source) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return Base64.getUrlEncoder().encodeToString(sha256.digest(source.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("encrypt error");
        }
    }
}
