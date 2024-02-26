package ru.ostap.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TaskDTO {
  private String title;
  private int progress;
  private List<CommentDTO> comments;
}
