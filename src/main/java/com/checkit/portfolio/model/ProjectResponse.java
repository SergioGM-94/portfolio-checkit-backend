package com.checkit.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {
    private Integer projectId;
    private String name;
    private String color;

    private List<UserResponse> guests;
}
