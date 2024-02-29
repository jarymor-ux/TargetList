package ru.ostap.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ostap.todolist.models.Comment;
import ru.ostap.todolist.models.Task;

import java.util.Optional;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  Optional<Comment> getCommentById(Long id);


}
