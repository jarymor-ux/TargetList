package ru.ostap.todolist.utils;

public class UserNotCreatedException extends RuntimeException {
    public UserNotCreatedException(String msg) {
        super(msg);
    }
}
