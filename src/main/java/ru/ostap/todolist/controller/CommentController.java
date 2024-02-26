package ru.ostap.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ostap.todolist.models.Comment;
import ru.ostap.todolist.service.CommentService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

  private final CommentService commentService;

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
    commentService.save(comment);
    return "redirect:/";
  }
}
