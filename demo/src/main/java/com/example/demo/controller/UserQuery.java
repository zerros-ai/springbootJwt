package com.example.demo.controller;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserQuery {


    private final UserService userService;

    public UserQuery(@Autowired UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public User getUser(@Argument String id){
        return userService.getUserById(id);
    }
}
