package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    public UserEntity getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }
    public UserEntity updateUser(String id, UserEntity user) {
        UserEntity userEntity =getUserById(id);
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        return userRepository.save(userEntity);
    }
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
