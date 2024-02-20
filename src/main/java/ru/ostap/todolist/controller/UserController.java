package ru.ostap.todolist.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ru.ostap.todolist.dto.UserDTO;
import ru.ostap.todolist.models.Task;
import ru.ostap.todolist.models.User;
import ru.ostap.todolist.security.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    
    @GetMapping
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model){
        if (userDetails != null) {
            model.addAttribute("tasks", userDetails.getTasks());
        }

        return "home/home-page";
    }


    

}
