package com.example.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private static final long EXPIRATION_TIME = 1000 * 3600; // 1시간

    private static final String secretKey = "replace_with_32_byte_secure_key_example_123456"; //랜덤키

    //key 객체 생성
    private Key getSignKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    //ID로 JWT 토큰 생성
    public String generateToken(String id){
        long expiration = System.currentTimeMillis() + EXPIRATION_TIME;
        String token = Jwts.builder()
                .subject(id)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ expiration))
                .signWith(getSignKey())
                .compact();

        // Redis에 JWT 저장(토큰 만료 시간과 함께)
        redisTemplate.opsForValue().set(token, id, expiration, TimeUnit.MILLISECONDS);
        return token;
    }


    public String getUserIdFromToken(String token){
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //유효성검사
    public boolean validateToken(String token){
        if(!redisTemplate.hasKey(token)){
            return false;
        }
        try {
            Jwts.parser()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token);
            System.out.println("token valid");
            return true;
        }catch (JwtException e){
            System.out.println("token invalid");
            return false;
        }
    }

    public void invalidateToken(String token){
        redisTemplate.delete(token);
    }
}
