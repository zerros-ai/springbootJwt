package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public User createUser(User user) {
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

}
