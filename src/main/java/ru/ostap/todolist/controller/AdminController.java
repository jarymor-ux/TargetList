package ru.ostap.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.ostap.todolist.models.User;
import ru.ostap.todolist.service.RegistrationService;
import ru.ostap.todolist.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RegistrationService registrationService;
    @Autowired
    public AdminController(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }
    
    @GetMapping
    public String adminPanel(Model model){
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getDetails());
        return "admin-panel/admin-panel";
    }

    @GetMapping("/all-users")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "admin-panel/all-users";
    }

    @DeleteMapping("/delete/{id}")
    public String  deleteUser(@PathVariable long id){
        userService.delete(id);

        return "redirect:/admin/all-users";
    }

    @GetMapping("/add-new")
    public String addNewUser(@ModelAttribute("user") User user){
        return "admin-panel/add-new";
    }

    @PutMapping("/add-new")
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-panel/add-new";
        }
        registrationService.save(user);
        return "redirect:/admin/all-users";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id).get());
        return "admin-panel/edit";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user ") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors())
            return "admin-panel/edit";

        userService.update(id, user);
        return "redirect:/admin/all-users";
    }




    
    
    
}
