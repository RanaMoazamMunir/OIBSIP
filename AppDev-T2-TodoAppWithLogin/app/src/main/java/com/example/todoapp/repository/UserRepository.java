package com.example.todoapp.repository;

import com.example.todoapp.model.User;

public interface UserRepository {

    long addUser(User user);

    User findByEmail(String email);

    boolean emailExists(String email);
}