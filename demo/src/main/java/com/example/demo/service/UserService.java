package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public String encrytPassword(String password) {
        return passwordEncoder.encode(password);
    }
    public boolean checkPassword(String password, String password2) {
        return passwordEncoder.matches(password, password2);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public User createUser(User user) {
        if(userRepository.existsById(user.getId())) {
            throw new RuntimeException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    public User updateUser(String id, User user) {
        User userEntity =getUserById(id);
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        return userRepository.save(userEntity);
    }
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public Page<User> getUserByRole(String role, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return userRepository.findByRole(role, pageable);
    }
}
