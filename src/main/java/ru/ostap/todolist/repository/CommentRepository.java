package ru.ostap.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ostap.todolist.models.Comment;
import ru.ostap.todolist.models.Task;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  Optional<Comment> getCommentById(Long id);
}
