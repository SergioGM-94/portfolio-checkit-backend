package com.checkit.portfolio.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.checkit.portfolio.model.Project;
import com.checkit.portfolio.model.ProjectResponse;
import com.checkit.portfolio.model.User;
import com.checkit.portfolio.model.UserResponse;
import com.checkit.portfolio.service.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
	private final ProjectService projectService;
   
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
    	Project newProject = projectService.createProject(project);
        return new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Integer projectId) {
    	Project project = projectService.getProjectById(projectId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        List<UserResponse> guests = project.getGuests().stream()
                .map(user -> new UserResponse(
                		user.getId(),
                		user.getFirstName(),
                		user.getLastName(),
                		user.getEmail(),
                		user.getUsername(),
                		user.getUsername1()))
                .collect(Collectors.toList());

        ProjectResponse response = new ProjectResponse(
                project.getProjectId(),
                project.getName(),
                project.getColor(),
                guests
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProjectResponse>> getProjectsByUserId(@PathVariable Integer userId) {
        List<Project> projects = projectService.getProjectsByUserId(userId);

        List<ProjectResponse> response = projects.stream().map(project -> {
            List<UserResponse> guests = project.getGuests().stream()
                    .map(user -> new UserResponse(
                            user.getId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getEmail(),
                            user.getUsername(),
                            user.getUsername1()))
                    .collect(Collectors.toList());

            return new ProjectResponse(
                    project.getProjectId(),
                    project.getName(),
                    project.getColor(),
                    guests
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/guest/{id}")
    public ResponseEntity<List<Project>> findProjectsByGuestId(@PathVariable Integer id){
        List<Project> projects = projectService.findProjectsByGuestId(id);
        if (projects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(projects);
    }
    
    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Integer projectId, @RequestBody Project project) {
    	project.setProjectId(projectId);
        Project updatedProject = projectService.updateProject(project);
        return ResponseEntity.ok(updatedProject);
    }
   
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{projectId}/guests/{userId}")
    public ResponseEntity<Project> addGuest(@PathVariable Integer projectId, @PathVariable Integer userId) {
        Project updatedProject = projectService.addGuest(projectId, userId);
        return ResponseEntity.ok(updatedProject);
    }
    
    @DeleteMapping("/{projectId}/guests/{userId}")
    public ResponseEntity<Project> removeGuest(@PathVariable Integer projectId, @PathVariable Integer userId) {
        Project updatedProject = projectService.removeGuest(projectId, userId);
        return ResponseEntity.ok(updatedProject);
    }

    @GetMapping("/{projectId}/guests")
    public ResponseEntity<List<UserResponse>> getGuests(@PathVariable Integer projectId) {
        List<User> guests = projectService.getGuests(projectId);

        List<UserResponse> response = guests.stream()
                .map(user -> new UserResponse(
                		user.getId(),
                		user.getFirstName(),
                		user.getLastName(),
                		user.getEmail(),
                		user.getUsername(),
                		user.getUsername1()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
