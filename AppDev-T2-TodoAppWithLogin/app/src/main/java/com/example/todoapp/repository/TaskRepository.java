package com.example.todoapp.repository;

import com.example.todoapp.model.Task;

import java.util.List;

public interface TaskRepository {

    long addTask(Task task);

    List<Task> getTasksForUser(int userId);

    boolean updateTask(Task task);

    boolean deleteTask(
            int taskId,
            int userId
    );
}