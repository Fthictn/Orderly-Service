package com.orderly.service;

import com.orderly.LightDTO.ProjectLightDTO;
import com.orderly.response.ProjectResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {
    ProjectResponse createNewProject(ProjectLightDTO entity);
    ProjectResponse getProjectsByUserId(int userId);
    ProjectResponse getAllProjects();
}
