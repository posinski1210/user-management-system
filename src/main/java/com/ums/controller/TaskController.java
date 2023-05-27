package com.ums.controller;

import com.ums.entity.Task;
import com.ums.entity.User;
import com.ums.service.TaskService;
import com.ums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class TaskController {
    private TaskService taskService;
    private UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService){
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/tasks")
    public String listTasks(Model model, Principal principal, SecurityContextHolderAwareRequestWrapper request){
        prepareTasksListModel(model,principal,request);
        model.addAttribute("onlyInProgress",false);
        return "/tasks";
    }

    @GetMapping("/tasks/in-progress")
    public String listTasksInProgress(Model model,Principal principal, SecurityContextHolderAwareRequestWrapper request){
        prepareTasksListModel(model,principal,request);
        model.addAttribute("onlyInProgress", true);
        return "/tasks";
    }

    @GetMapping("/task/create")
    public String showEmptyTaskForm(Model model,Principal principal, SecurityContextHolderAwareRequestWrapper request){
        String email = principal.getName();
        User user = userService.getUserByEmail(email);

        Task task = new Task();
        task.setCreatorName(user.getName());
        if(request.isUserInRole("ROLE_USER")){
            task.setOwner(user);
        }
        model.addAttribute("task",task);
        return "task-new";
    }

    @PostMapping("/task/create")
    public String createTask(@Valid Task task, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "task-new";
        }
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/task/edit/{id}")
    public String showFilledTaskForm(@PathVariable Long id, Model model){
        model.addAttribute("task",taskService.getTaskById(id));
        return "task-edit";
    }

    @PostMapping("/task/edit/{id}")
    public String updateTask(@Valid Task task, BindingResult bindingResult,@PathVariable Long id, Model model){
        if(bindingResult.hasErrors()){
            return "task-edit";
        }
        taskService.updateTask(id,task);
        return "redirect:/tasks";
    }
    @PostMapping("/task/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id){
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @PostMapping("/profile/mark-done/{id}")
    public  String setTaskCompleted(@PathVariable Long id){
        taskService.setTaskCompleted(id);
        return "redirect:/profile";
    }
    @PostMapping("/profile/unmark-done/{id}")
    public  String setTaskNotCompleted(@PathVariable Long id){
        taskService.setTaskNotCompleted(id);
        return "redirect:/profile";
    }


    private void prepareTasksListModel(Model model, Principal principal, SecurityContextHolderAwareRequestWrapper request) {
        String email = principal.getName();
        User signedUser = userService.getUserByEmail(email);
        boolean isAdminSigned = request.isUserInRole("ROLE_ADMIN");

        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("users",taskService.findAll());
        model.addAttribute("signedUser", signedUser);
        model.addAttribute("isAdminSigned", isAdminSigned);
    }


}
