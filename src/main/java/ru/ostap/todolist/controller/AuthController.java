package ru.ostap.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.ostap.todolist.dto.UserDTO;
import ru.ostap.todolist.service.RegistrationService;

import javax.validation.Valid;
@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;
    @Autowired
    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") UserDTO userDTO){
        return "auth/registration";
    }

    @PutMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid UserDTO user,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
        registrationService.saveWithDto(user);
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "auth/form-login";
    }

}
