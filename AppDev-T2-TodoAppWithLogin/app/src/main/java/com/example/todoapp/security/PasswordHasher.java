package com.example.todoapp.security;

public interface PasswordHasher {

    String hash(String password);

    boolean verify(
            String password,
            String storedHash
    );
}