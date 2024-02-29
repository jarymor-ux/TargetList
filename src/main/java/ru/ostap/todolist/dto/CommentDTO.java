package ru.ostap.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CommentDTO {
  private String commentMSG;
  private Timestamp created_at;
}
