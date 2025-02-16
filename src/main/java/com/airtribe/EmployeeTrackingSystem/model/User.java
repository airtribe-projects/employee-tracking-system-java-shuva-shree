package com.airtribe.EmployeeTrackingSystem.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String password;
    private String email;
    private String role;
    private String isEnabled;

    public User() {}

    public User(long userId, String password, String email, String role, String isEnabled) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isEnabled = isEnabled;
    }

    public User(String email, String space, Set<GrantedAuthority> authoritySet) {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }


}
