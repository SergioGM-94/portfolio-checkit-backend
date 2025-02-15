package com.checkit.portfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.checkit.portfolio.model.Project;
import com.checkit.portfolio.model.User;
import com.checkit.portfolio.repository.ProjectRepository;
import com.checkit.portfolio.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
	private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Optional<Project> getProjectById(Integer projectId) {
        return projectRepository.findById(projectId);
    }

    public List<Project> getProjectsByUserId(Integer userId) {
        return projectRepository.findByUserId(userId);
    }

    public List<Project> findProjectsByGuestId(Integer userId) {
        return projectRepository.findByGuests_Id(userId);
    }

    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }
    
    public void deleteProject(Integer projectId) {
    	projectRepository.deleteById(projectId);
    }

    public Project addGuest(Integer projectId, Integer userId) {
    	Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
     
        if (project.getGuests().contains(user)) {
            throw new RuntimeException("El usuario ya está invitado a este proyecto");
        }

        project.getGuests().add(user);
        return projectRepository.save(project);
    }

    public Project removeGuest(Integer projectId, Integer userId) {
    	Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        project.getGuests().remove(user);
        return projectRepository.save(project);
    }

    public List<User> getGuests(Integer projectId) {
    	Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        return project.getGuests();
    }
}
