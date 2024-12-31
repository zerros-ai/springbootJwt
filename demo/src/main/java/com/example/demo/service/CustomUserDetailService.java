package com.example.demo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        if(!id.equals("admin")){
            throw new UsernameNotFoundException(id);
        }
        return new User("admin","qwe123!@#",new ArrayList<>());
    }
}
