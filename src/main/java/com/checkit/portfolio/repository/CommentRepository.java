package com.checkit.portfolio.repository;

import com.checkit.portfolio.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByTaskTaskIdAndActiveTrue(Integer taskId);
    List<Comment> findByUserIdAndActiveTrue(Integer userId);
    Optional<Comment> findByCommentIdAndActiveTrue(Integer commentId);
    List<Comment> findByUserIdAndTaskTaskIdAndActiveTrue(Integer userId, Integer taskId);
}
