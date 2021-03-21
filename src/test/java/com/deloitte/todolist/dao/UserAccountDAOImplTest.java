package com.deloitte.todolist.dao;

import com.deloitte.todolist.model.Task;
import com.deloitte.todolist.repository.TaskRepository;
import com.deloitte.todolist.repository.UserAccountRepository;
import com.deloitte.todolist.service.UserAccountService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserAccountDAOImplTest {


    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserAccountDAO userAccountDAO;

    @BeforeEach
    private void setUp() throws Exception {
        ArrayList<Task> taskList = new ArrayList<>();

        this.userAccountRepository.deleteAll();
        this.taskRepository.deleteAll();

        taskList.add(new Task(
                "Update Documentation",
                "The confluence documentation is out of date and needs an update",
                LocalDate.of(2020, 10, 10).toString(),
                "test",
                false
        ));

        taskList.add(new Task(
                "Create POC",
                "Create Proof of Concept App for Business",
                LocalDate.of(2021, 3, 20).toString(),
                "test",
                true
        ));

        taskRepository.saveAll(taskList);
    }

    @Test
    public void validateAllTasksFoundForUser() {
        List<Task> taskList = userAccountDAO.findAllTasksByAssignedUser("test");
        Assert.assertEquals("test", taskList.get(0).getAssignedUser());
        Assert.assertEquals("The confluence documentation is out of date and needs an update", taskList.get(0).getDescription());
        Assert.assertEquals("Update Documentation", taskList.get(0).getTaskName());
        Assert.assertFalse(taskList.get(0).getIsChecked());
        Assert.assertEquals("2020-10-10", taskList.get(0).getLastUpdated());

        Assert.assertEquals("test", taskList.get(1).getAssignedUser());
        Assert.assertEquals("Create Proof of Concept App for Business", taskList.get(1).getDescription());
        Assert.assertEquals("Create POC", taskList.get(1).getTaskName());
        Assert.assertTrue(taskList.get(1).getIsChecked());
        Assert.assertEquals("2021-03-20", taskList.get(1).getLastUpdated());
    }

    @Test
    public void validateTaskDeleted() {

        List<Task> taskList = userAccountDAO.findAllTasksByAssignedUser("test");
        int deleteId = (int) taskList.get(0).getId();

        userAccountDAO.deleteTaskById(deleteId);
        taskList = userAccountDAO.findAllTasksByAssignedUser("test");
        Assert.assertEquals(1, taskList.size());
    }

    @Test
    public void validateNewTaskAdded() {
        Task task = new Task("testTask", "This task is for testing", LocalDate.of(2020, 10, 9).toString(), "test", true);
        userAccountDAO.saveTask(task);
        List<Task> taskList = userAccountDAO.findAllTasksByAssignedUser("test");
        Assert.assertEquals("testTask", taskList.get(2).getTaskName());
        Assert.assertEquals("This task is for testing", taskList.get(2).getDescription());
        Assert.assertEquals(LocalDate.of(2020, 10, 9).toString(), taskList.get(2).getLastUpdated());
        Assert.assertEquals("test", taskList.get(2).getAssignedUser());
        Assert.assertTrue(taskList.get(2).getIsChecked());
    }

    @Test
    public void validateTaskUpdate() {
        Task task = new Task("testTask", "This task is for testing", LocalDate.of(2020, 10, 9).toString(), "test", true);

        List<Task> taskList = userAccountDAO.findAllTasksByAssignedUser("test");
        int updateId = (int) taskList.get(0).getId();
        task.setId(updateId);

        userAccountDAO.saveTask(task);

        taskList = userAccountDAO.findAllTasksByAssignedUser("test");
        Assert.assertEquals("testTask", taskList.get(0).getTaskName());
        Assert.assertEquals("This task is for testing", taskList.get(0).getDescription());
        Assert.assertEquals(LocalDate.of(2020, 10, 9).toString(), taskList.get(0).getLastUpdated());
        Assert.assertEquals("test", taskList.get(0).getAssignedUser());
        Assert.assertTrue(taskList.get(0).getIsChecked());
    }
}
