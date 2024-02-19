package ru.ostap.todolist.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ru.ostap.todolist.dto.UserDTO;
import ru.ostap.todolist.models.User;
import ru.ostap.todolist.security.UserDetails;

@Controller
public class UserController {
    
    @GetMapping
    public String home(){
        return "home/home-page";
    }


    

}
