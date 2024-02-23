package ru.ostap.todolist.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class UserErrorResponse {
    private String msg;
    private Timestamp timestamp;
}
