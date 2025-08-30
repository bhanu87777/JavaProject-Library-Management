package com.library.models;

import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String role;     // "student" or "librarian"
    private String password;

    // Constructor
    public User(String id, String name, String role, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.password = password;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    // For security, avoid returning plain password in toString()
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLibrarian() {
        return "librarian".equalsIgnoreCase(role);
    }

    public boolean isStudent() {
        return "student".equalsIgnoreCase(role);
    }

    @Override
    public String toString() {
        return "[User ID=" + id + ", Name=" + name + ", Role=" + role + "]";
    }
}
