package com.checkit.portfolio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checkit.portfolio.model.Task;
import com.checkit.portfolio.service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
	private final TaskService taskService;  
	
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task newTask = taskService.createTask(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }
  
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable Integer projectId) {
        List<Task> tasks = taskService.getTasksByProjectId(projectId);
        return tasks.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tasks);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Integer userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        return tasks.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer taskId) {
        Optional<Task> task = taskService.getTaskById(taskId);
        return task.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/project/{projectId}/title/{title}")
    public ResponseEntity<Task> getTaskByTitleAndProject(@PathVariable Integer projectId, @PathVariable String title) {
        Optional<Task> task = taskService.getTaskByTitleAndProject(title, projectId);
        return task.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer taskId, @RequestBody Task task) {
        task.setTaskId(taskId);
        Task updatedTask = taskService.updateTask(task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
    	taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{taskId}/checked")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Integer taskId, @RequestBody Boolean checked) {
        Task updatedTask = taskService.updateTaskStatus(taskId, checked);
        return updatedTask != null ? ResponseEntity.ok(updatedTask)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    @PatchMapping("/{taskId}/restore")
    public ResponseEntity<Task> restaurarTarea(@PathVariable Integer taskId) {
        Task restoredTask = taskService.restoreTask(taskId);
        return ResponseEntity.ok(restoredTask);
    }
}
