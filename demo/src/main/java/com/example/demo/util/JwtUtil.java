package com.example.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String secretKey = "replace_with_32_byte_secure_key_example_123456"; //랜덤키

    //key 객체 생성
    private Key getSignKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    //ID로 JWT 토큰 생성
    public String generateToken(String id){
        long expiration = 1000 * 3600;
        return Jwts.builder()
                .subject(id)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ expiration))
                .signWith(getSignKey())
                .compact();
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
}
