package ru.ostap.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ostap.todolist.models.Comment;
import ru.ostap.todolist.repository.CommentRepository;
import ru.ostap.todolist.repository.TaskRepository;
import ru.ostap.todolist.utils.DtoConverter;



@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentRepository commentRepository;
  private final TaskRepository taskRepository;
  private  final DtoConverter dtoConverter;

  public Comment getCommentById(long id) throws Exception {
    if (commentRepository.getCommentById(id).isPresent()) {
      return commentRepository.getCommentById(id).get();
    } else {
      throw new Exception("Comment not found");
    }
  }

  public void update(long id, Comment updatedComment) {
    Comment commentToBeUpdated = commentRepository.findById(id).get();

    updatedComment.setId(id);
    updatedComment.setTask(commentToBeUpdated.getTask());
    updatedComment.setCommentMSG(commentToBeUpdated.getCommentMSG());
    updatedComment.setCreated_at(commentToBeUpdated.getCreated_at());

    commentRepository.save(updatedComment);
  }

  public void saveNew(Comment comment) {
    Comment comment1 = commentRepository.findById(comment.getId()).get();
    comment.setTask(comment1.getTask());
    commentRepository.save(comment);
  }

  public void delete(long id){
    commentRepository.delete(commentRepository.findById(id).get());    // id приходит, но объект нет
  }
}
