package com.deloitte.todolist.controller;

import com.deloitte.todolist.dao.UserAccountDAO;
import com.deloitte.todolist.model.Task;
import com.deloitte.todolist.model.UserAccount;
import com.deloitte.todolist.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class RestController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserAccountDAO userAccountDAO;

    public RestController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);
        return "login";
    }

    @GetMapping("/todo_list")
    public String showTodoList(Model model) {
        String username = userAccountService.getCurrentUser();
        model.addAttribute("username", username);

        List<Task> taskList = userAccountDAO.findAllTasksByAssignedUser(username);
        model.addAttribute("taskList", taskList);
        model.addAttribute("task", new Task());

        return "todo_list";
    }

    @PostMapping(value = "/todo_list/updateTask", params = "action=updateTask")
    public String updateTask(Task task) {

        task.setLastUpdated(LocalDate.now().toString());
        userAccountDAO.saveTask(task);

        return "redirect:/todo_list";
    }

    @PostMapping(value = "/todo_list/addTask", params = "action=newTask")
    public String addTask(Task task) {
        String username = userAccountService.getCurrentUser();

        task.setAssignedUser(username);
        task.setIsChecked(false);
        task.setLastUpdated(LocalDate.now().toString());

        userAccountDAO.saveTask(task);

        return "redirect:/todo_list";
    }

    @PostMapping(value = "/todo_list/{id}", params = "action=delete")
    public String deleteTask(@PathVariable long id) {
        userAccountDAO.deleteTaskById(id);
        return "redirect:/todo_list";
    }
}