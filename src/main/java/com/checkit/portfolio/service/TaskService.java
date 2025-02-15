package com.checkit.portfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.checkit.portfolio.model.Task;
import com.checkit.portfolio.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
	private final TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getTasksByProjectId(Integer projectId) {
        return taskRepository.findByProjectProjectIdAndActiveTrue(projectId);
    }
    
    public List<Task> getTasksByUserId(Integer userId) {
        return taskRepository.findByUserIdAndActiveTrue(userId);
    }

    public Optional<Task> getTaskById(Integer taskId) {
        return taskRepository.findByTaskIdAndActiveTrue(taskId);
    }
  
    public Optional<Task> getTaskByTitleAndProject(String title, Integer projectId) {
        return taskRepository.findByTitleAndProjectProjectIdAndActiveTrue(title, projectId);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }
    
    public void deleteTask(Integer taskId) {
        Optional<Task> tareaOpt = taskRepository.findById(taskId);
        if (tareaOpt.isPresent()) {
            Task task = tareaOpt.get();
            task.setActive(false);
            taskRepository.save(task);
        }
    }

    public Task updateTaskStatus(Integer taskId, Boolean checked) {
        Optional<Task> tareaOpt = taskRepository.findByTaskIdAndActiveTrue(taskId);
        if (tareaOpt.isPresent()) {
            Task task = tareaOpt.get();
            task.setChecked(checked);
            return taskRepository.save(task);
        }
        return null;
    }
    
    public Task restoreTask(Integer taskId) {
        Optional<Task> tareaOpt = taskRepository.findById(taskId);
        if (tareaOpt.isPresent()) {
            Task task = tareaOpt.get();
            
            task.setActive(true);

            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Tarea no encontrada");
        }
    }

}
