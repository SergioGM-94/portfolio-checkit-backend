package com.checkit.portfolio.repository;

import com.checkit.portfolio.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Optional<Project> findByProjectId(Integer projectId);
    List<Project> findByUserId(Integer userId);
    Optional<Project> findByName(String name);
    List<Project> findByGuests_Id(Integer userId);
}
