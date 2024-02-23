package ru.ostap.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ostap.todolist.models.Task;
import ru.ostap.todolist.models.User;
import ru.ostap.todolist.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public void save(Task task) {
        taskRepository.save(task);
    }

    public Optional<Task> getTaskById(long id) {
        return taskRepository.getTaskById(id);
    }

    public void update(long id, Task updatedTask) {
        Task taskToBeUpdated = taskRepository.findById(id).get();

        updatedTask.setId(id);
        updatedTask.setUser(taskToBeUpdated.getUser());
        updatedTask.setComments(taskToBeUpdated.getComments()); // чтобы не терялась связь при обновлении

        taskRepository.save(updatedTask);

        //TODO:page not refresh values
    }

    public List<Task> getTasksByUser(User user) throws Exception {
        Optional<List<Task>> tasks = taskRepository.getTaskByUser(user);

        if (tasks.isPresent()) {
            return taskRepository.getTaskByUser(user).get();
        }else {
            throw new Exception("Task list is empty");
        }

    }
}
