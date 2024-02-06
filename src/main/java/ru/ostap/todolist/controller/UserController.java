package ru.ostap.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ostap.todolist.service.UserService;

@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/home")
    public String home(){
        return "home/home-page";
    }


    

}
