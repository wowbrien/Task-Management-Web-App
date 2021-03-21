package com.deloitte.todolist.dao;

import com.deloitte.todolist.model.Task;

import java.util.List;

public interface UserAccountDAO {

    List<Task> findAllTasksByAssignedUser(String username);

    void saveTask(Task task);

    void deleteTaskById(long id);
}
