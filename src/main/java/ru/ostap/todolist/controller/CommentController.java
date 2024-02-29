package ru.ostap.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ostap.todolist.models.Comment;
import ru.ostap.todolist.service.CommentService;
import ru.ostap.todolist.service.TaskService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

  private final CommentService commentService;
  private final TaskService taskService;

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable long id, Model model) throws Exception {
    model.addAttribute("comment", commentService.getCommentById(id));

    return "user/task/comment/edit";
  }

  @PatchMapping("/edit/{id}")
  private String update(
      @ModelAttribute("comment") @Valid Comment comment, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "user/task/comment/edit";
    }
    commentService.saveNew(comment);
    return "redirect:/";
  }

  @DeleteMapping("/delete/{id}")
  public String delete(@PathVariable long id){
    commentService.delete(id);
    return "redirect:/";
  }

  @GetMapping("/create/{id}")
  public String create(@ModelAttribute("comment") Comment comment, @ModelAttribute("taskId") Long id){
    comment.setTask(taskService.getTaskById(id).get());
    return "user/task/comment/add-new";
  }

  @PutMapping("/create")
  public String createComment(@ModelAttribute("comment") @Valid Comment comment, BindingResult bindingResult){

    if (bindingResult.hasErrors()) {
      return "user/task/comment/add-new";
    }



    commentService.saveNew(comment);

    return "redirect:/";
  }
}
