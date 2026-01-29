package com.example.demo.Util;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TokenStore {

    private static final Map<String, Long> TOKENS = new ConcurrentHashMap<>();

    public static String createToken(Long userId) {
        String token = UUID.randomUUID().toString();
        TOKENS.put(token, userId);
        return token;
    }

    public static Long getUserId(String token) {
        return TOKENS.get(token);
    }
}
