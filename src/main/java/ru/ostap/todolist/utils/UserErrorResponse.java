package ru.ostap.todolist.utils;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserErrorResponse {
  private String msg;
  private Timestamp timestamp;
}
