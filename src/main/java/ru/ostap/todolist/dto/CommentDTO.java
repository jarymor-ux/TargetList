package ru.ostap.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class CommentDTO {
    private String commentMSG;
    private Timestamp created_at;
}
