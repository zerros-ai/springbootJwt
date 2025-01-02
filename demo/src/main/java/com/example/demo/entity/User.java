package com.example.demo.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "User")
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String password;
    private String role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}
}
