package ru.ostap.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ostap.todolist.models.Comment;
import ru.ostap.todolist.models.Task;
import ru.ostap.todolist.repository.CommentRepository;
import ru.ostap.todolist.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;


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

    public void save(Comment comment){
        Comment comment1 = commentRepository.findById(comment.getId()).get();
//        Task task = taskRepository.getTaskById(comment1.getTask().getId()).get();

        comment.setTask(comment1.getTask());
        commentRepository.save(comment);
    }
}
