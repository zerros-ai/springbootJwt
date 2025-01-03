package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        try {
            System.out.println("loadUserByUsername");
                return userRepository.findById(id)
                        .map(user-> new org.springframework.security.core.userdetails.User(
                                user.getId(),
                                user.getPassword(),
                                List.of(new SimpleGrantedAuthority(user.getRole()))
                        ))
                        .orElseThrow(() -> new UsernameNotFoundException("User not found: "+id));
        }catch (Exception e) {
            throw new InternalAuthenticationServiceException("Failed to fetch user details", e);
        }
    }
}
