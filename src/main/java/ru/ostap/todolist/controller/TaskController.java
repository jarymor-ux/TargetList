package ru.ostap.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ostap.todolist.models.Task;
import ru.ostap.todolist.security.UserDetails;
import ru.ostap.todolist.service.TaskService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable("id") long id){
        model.addAttribute("task", taskService.getTaskById(id).get());
        return "user/task/edit";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("task ") @Valid Task task, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "user/task/edit";
        }
        taskService.update(id, task);

        return "redirect:/";
    }

    @GetMapping("/add-new")
    public String addNewTaskForm(@ModelAttribute("task") Task task){
        return "user/task/add-new";
    }


    @PutMapping("/add-new")
    public String addNewTask(@ModelAttribute("task") @Valid Task task,
                             BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails){

        if (bindingResult.hasErrors()) {
            return "user/task/add-new";
        }
        taskService.saveNew(task, userDetails);
        return "redirect:/";
    }

}
