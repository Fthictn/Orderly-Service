package com.orderly.LightDTO.orderly.service;

import com.orderly.LightDTO.orderly.LightDTO.ProjectLightDTO;
import com.orderly.LightDTO.orderly.response.ProjectResponse;
import org.springframework.stereotype.Service;

public interface ProjectService {
    com.orderly.LightDTO.orderly.response.ProjectResponse createNewProject(ProjectLightDTO entity);
    com.orderly.LightDTO.orderly.response.ProjectResponse getProjectsByUserId(int userId);
    ProjectResponse getAllProjects();
}
