package ru.ostap.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ostap.todolist.models.Task;
import ru.ostap.todolist.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> getTaskById(Long id);
    Optional<List<Task>> getTaskByUser(User user);
}
