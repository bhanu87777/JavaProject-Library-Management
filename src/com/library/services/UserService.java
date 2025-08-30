package com.library.services;

import com.library.models.User;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<>();

    // Add user
    public void addUser(User user) {
        users.add(user);
        System.out.println("✅ User added: " + user);
    }

    // Remove user by ID
    public boolean removeUser(String userId) {
        return users.removeIf(u -> u.getId().equals(userId));
    }

    // List all users
    public List<User> listUsers() {
        return new ArrayList<>(users);
    }

    // Get user by ID (helper method)
    public User getUserById(String userId) {
        return users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    // Get user by name (for login)
    public User getUserByName(String name) {
        return users.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
