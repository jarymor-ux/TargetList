package ru.ostap.todolist.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ostap.todolist.security.UserDetails;

@Controller
public class UserController {

    @GetMapping
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) throws Exception {

        if (userDetails != null) {
            model.addAttribute("tasks", userDetails.getTasks());
        }

        return "home/home-page";
    }


}
