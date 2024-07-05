package com.authen.sec.authentication;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {
    private List<User> users;

    public AuthenticationService() {
        users = new ArrayList<>();

        // Hard code user profiles
        User user1 = User.createHardcodedUser("john_doe", "password123");
        User user2 = User.createHardcodedUser("jane_smith", "securePass");

        users.add(user1);
        users.add(user2);
    }

    // Check if a username is already taken
    public boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // Register a new user
    public void registerUser(String username, String password) {
        // Check if the username is already in use
        if (isUsernameTaken(username)) {
            System.out.println("Username is already taken. Please choose another.");
            return;
        }

        // Create a new user and add it to the list
        User newUser = new User(username, password, generatePublicKey());
        users.add(newUser);
        System.out.println("User registered successfully.");
    }

    // Authenticate a user
    public boolean authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                // Check if the provided password matches the user's password
                if (user.checkPassword(password)) {
                    System.out.println("Login successful.");
                    return true;
                } else {
                    System.out.println("Incorrect password. Please try again.");
                    return false;
                }
            }
        }
        System.out.println("User not found. Please register.");
        return false;
    }

    // Additional method to get public key for a user
    public String getUserPublicKey(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getPublicKey();
            }
        }
        return null; // User not found
    }

    // Generate a public key for a new user
    private String generatePublicKey() {
        // Implement your public key generation logic here
        // For simplicity, I'm returning a placeholder value
        return "placeholder_public_key";
    }
}
