package com.example.todoapp.model;

public class User {

    private int id;
    private final String name;
    private final String email;
    private final String passwordHash;

    public User(
            int id,
            String name,
            String email,
            String passwordHash) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public User(
            String name,
            String email,
            String passwordHash) {

        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setId(int id) {
        this.id = id;
    }
}