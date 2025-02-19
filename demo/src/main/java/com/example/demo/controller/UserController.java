package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "User Api",description = "사용자 관련 API")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    //계정 생성
    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);}

    //계정 조회
    @Operation(summary = "사용자 목록 조회", description = "모든 사용자를 조회하는 API")
    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        String id = authentication.getName();
        String name = userService.getUserById(id).getName();
        String role = userService.getUserById(id).getRole();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", id);
        userInfo.put("name",name);
        userInfo.put("role",role);
        userInfo.put("message", "User info retrieved successfully");
        return ResponseEntity.ok(userInfo);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<User>> getUserList(@RequestParam String role,
                                                  @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(userService.getUserByRole(role, page, size));
    }
}

