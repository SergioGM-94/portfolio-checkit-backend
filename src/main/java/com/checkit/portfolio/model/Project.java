package com.checkit.portfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer projectId;
    @Column(nullable = false)
    private String name;
    private String color;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @ManyToMany
    @JoinTable(
    		name = "project_guests",
    		joinColumns = @JoinColumn(name = "project_id"),
    		inverseJoinColumns = @JoinColumn(name = "user_id")
    		)
    private List<User> guests;
}
