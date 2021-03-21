package com.deloitte.todolist.dao.impl;

import com.deloitte.todolist.dao.UserAccountDAO;
import com.deloitte.todolist.model.Task;
import com.deloitte.todolist.repository.TaskRepository;
import com.deloitte.todolist.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAccountDAOImpl implements UserAccountDAO {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private TaskRepository taskRepository;


    public List<Task> findAllTasksByAssignedUser(String username) {
        return this.taskRepository.findAllByAssignedUser(username);
    }

    public void saveTask(Task task) {
        this.taskRepository.save(task);
    }

    public void deleteTaskById(long id) {
        this.taskRepository.deleteById(id);
    }
}
