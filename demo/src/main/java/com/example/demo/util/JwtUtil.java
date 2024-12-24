package com.example.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private String secretKey = "boss"; //랜덤키
    private long expiration = 1000 * 3600;

    //key 객체 생성
    private Key getSignKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    //ID로 JWT 토큰 생성
    public String generateToken(String id){
        return Jwts.builder()
                .subject(id)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignKey())
                .compact();
    }

    public String getUserIdfromToken(String token){
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
                    .parseClaimsJwt(token);
            return true;
        }catch (JwtException e){
            return false;
        }
    }
}
