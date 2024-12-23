package com.example.demo.controller;

import com.example.demo.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<> login(@RequestParam("id") String id, @RequestParam("password") String password) {
        //1.사용자명/비밀번호 검증(spring security authenticationManager 활용)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(id, password));

        //2.인증 성공 시, JWT 토큰 생성
        String token = jwtUtil.generateToken(id);

        //3.토큰을 바디로 담아 반환 (또는 Authorization 헤더에 같이 담아 전송도 가능)
        return ResponseEntity.ok().body("{\"token\": \"" + token + "\"}");
    }


}
