package com.deloitte.todolist;

import com.deloitte.todolist.model.Task;
import com.deloitte.todolist.model.UserAccount;
import com.deloitte.todolist.repository.TaskRepository;
import com.deloitte.todolist.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class DatabaseInitialiser implements CommandLineRunner {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private TaskRepository taskRepository;

    public DatabaseInitialiser(UserAccountRepository userAccountRepository, TaskRepository taskRepository) {
        this.userAccountRepository = userAccountRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ArrayList<UserAccount> userAccountList = new ArrayList<>();
        ArrayList<Task> taskList = new ArrayList<>();

        //password encoded used in conjunction with Spring security
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //remove anything that might have persisted in the DB
        this.userAccountRepository.deleteAll();
        this.taskRepository.deleteAll();

        //add users to list for db
        userAccountList.add(new UserAccount("test", encoder.encode("pwd123")));
        userAccountList.add(new UserAccount("test2", encoder.encode("pwd")));
        userAccountList.add(new UserAccount("test3", encoder.encode("pwd2")));

        //add tasks to list for db
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

        taskList.add(new Task(
                "Test3 task",
                "This is a task for Test3 only",
                LocalDate.of(2020, 11, 11).toString(),
                "test3",
                true
        ));

        //save users & tasks to db
        userAccountRepository.saveAll(userAccountList);
        taskRepository.saveAll(taskList);
    }
}
