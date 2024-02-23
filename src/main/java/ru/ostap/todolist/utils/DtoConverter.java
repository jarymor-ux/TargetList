package ru.ostap.todolist.utils;

import org.springframework.stereotype.Component;
import ru.ostap.todolist.dto.CommentDTO;
import ru.ostap.todolist.dto.TaskDTO;
import ru.ostap.todolist.dto.UserDTO;
import ru.ostap.todolist.models.Comment;
import ru.ostap.todolist.models.Task;
import ru.ostap.todolist.models.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoConverter {
    public UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setTasks(convertTasksListToTasksDTOList(user.getTasks()));
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        userDTO.setEnabled(user.isEnabled());
        return userDTO;
    }

    private List<TaskDTO> convertTasksListToTasksDTOList(List<Task> tasks) {
        List<TaskDTO> taskDTOS = new ArrayList<>();
        for (Task task : tasks) {
            taskDTOS.add(
                    new TaskDTO(
                            task.getTitle(),
                            task.getProgress(),
                            commentListToCommentDTOList(task.getComments())));
        }
        return taskDTOS;
    }

    private List<CommentDTO> commentListToCommentDTOList(List<Comment> comments) {
        List<CommentDTO> commentDTO = new ArrayList<>();
        for (Comment comment : comments) {
            commentDTO.add(new CommentDTO(comment.getComment(), comment.getCreated_at()));
        }
        return commentDTO;
    }

    public User convertUserDTOtoUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setEnabled(userDTO.isEnabled());
        return user;
    }
}
