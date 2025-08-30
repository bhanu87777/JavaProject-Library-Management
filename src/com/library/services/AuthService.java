package com.library.services;

import com.library.models.User;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, String> credentials = new HashMap<>(); // username -> password
    private UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    // Register credentials (link user with login)
    public void registerUser(User user) {
        credentials.put(user.getName(), user.getPassword());
        userService.addUser(user);
    }

    // Login method
    public User login(String username, String password) {
        if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
            User user = userService.getUserByName(username);
            System.out.println("✅ Login successful: " + user);
            return user;
        }
        System.out.println("❌ Invalid username or password.");
        return null;
    }
}
