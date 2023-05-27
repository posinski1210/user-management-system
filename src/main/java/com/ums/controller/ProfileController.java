package com.ums.controller;

import com.ums.entity.User;
import com.ums.service.TaskService;
import com.ums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class ProfileController {
    private UserService userService;
    private TaskService taskService;

    @Autowired
    public ProfileController(UserService userService,TaskService taskService){
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model, Principal principal){
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("user",user);
        model.addAttribute("tasksOwned",taskService.findByOwnerOrderByDateDesc(user));
        return "profile";
    }

    @GetMapping("/profile/mark-down/{taskId}")
    public String setTaskCompleted(@PathVariable Long taskId){
        taskService.setTaskCompleted(taskId);
        return "redirect:/profile";
    }

    @GetMapping("/profile/unmark-done/{taskId}")
    public String setTaskNotCompleted(@PathVariable Long taskId){
        taskService.setTaskNotCompleted(taskId);
        return "redirect:/profile";
    }
}
