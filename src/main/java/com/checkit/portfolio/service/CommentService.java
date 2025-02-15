package com.checkit.portfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.checkit.portfolio.model.Comment;
import com.checkit.portfolio.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;	
	
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Optional<Comment> getCommentById(Integer id) {
        return commentRepository.findByCommentIdAndActiveTrue(id);
    }

    public List<Comment> getCommentsByUserId(Integer userId) {
        return commentRepository.findByUserIdAndActiveTrue(userId);
    }

    public List<Comment> getCommentsByTaskId(Integer taskId) {
        return commentRepository.findByTaskTaskIdAndActiveTrue(taskId);
    }

    public List<Comment> getCommentsByUserIdAndTaskId(Integer userId, Integer taskId) {
        return commentRepository.findByUserIdAndTaskTaskIdAndActiveTrue(userId, taskId);
    }

    public void deleteCommentById(Integer id) {
        Optional<Comment> optComment = commentRepository.findById(id);
        if (optComment.isPresent()) {
            Comment comment = optComment.get();
            comment.setActive(false);
            commentRepository.save(comment);
        }
    }

    public Comment restoreComment(Integer id) {
        Optional<Comment> optComment = commentRepository.findById(id);
        if (optComment.isPresent()) {
            Comment comment = optComment.get();
            comment.setActive(true);
            return commentRepository.save(comment);
        } else {
            throw new RuntimeException("Comentario no encontrado");
        }
    }
}
