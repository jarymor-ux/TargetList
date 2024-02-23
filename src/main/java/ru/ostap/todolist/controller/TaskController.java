package ru.ostap.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ostap.todolist.models.Task;
import ru.ostap.todolist.models.User;
import ru.ostap.todolist.service.TaskService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id){
        model.addAttribute("task", taskService.getTaskById(id).get());
        return "user/task/edit";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("task ") @Valid Task task, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors())
            return "user/task/edit";
        taskService.update(id, task);
        return "redirect:/";
    }

}
