package ru.ostap.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.ostap.todolist.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public String adminPanel(Model model){
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        return "admin-panel/admin-panel";
    }

    //TODO:add delete, input, edit, ban, show list users - functions
    
    
}
