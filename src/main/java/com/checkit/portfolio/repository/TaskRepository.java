package com.checkit.portfolio.repository;

import com.checkit.portfolio.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByProjectProjectIdAndActiveTrue(Integer projectId);
    List<Task> findByUserIdAndActiveTrue(Integer userId);
    Optional<Task> findByTaskIdAndActiveTrue(Integer taskId);
    Optional<Task> findByTitleAndProjectProjectIdAndActiveTrue(String title, Integer projectId);
}
