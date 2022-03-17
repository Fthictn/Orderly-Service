package com.orderly.service;

import com.orderly.LightDTO.ProjectLightDTO;
import com.orderly.entity.ProjectEntity;
import com.orderly.entity.UserEntity;
import com.orderly.messages.Messages;
import com.orderly.repository.ProjectRepository;
import com.orderly.response.ProjectResponse;
import com.orderly.utils.EntityToDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class.getName());

    private final ProjectRepository projectRepository;

    private final EntityToDTOConverter converter;

    private List<ProjectEntity> projectResponse = new ArrayList<>();

    public ProjectServiceImpl(ProjectRepository projectRepository, EntityToDTOConverter converter) {
        this.projectRepository = projectRepository;
        this.converter = converter;
    }

    @Override
    public ProjectResponse createNewProject(ProjectLightDTO entity) {
        ProjectResponse response = new ProjectResponse();
        projectResponse = new ArrayList<>();
        List<ProjectLightDTO> lightList = new ArrayList<>();
        ProjectEntity existingEntity = projectRepository.findByProjectCode(entity.getProjectCode());
        if(existingEntity == null){
            ProjectEntity entityToSave = new ProjectEntity();
            entityToSave.setProjectCode(entity.getProjectCode());
            entityToSave.setProjectName(entity.getProjectName());
            UserEntity userEntity = new UserEntity();
            userEntity.setId(entity.getUserEntity().getId());
            entityToSave.setUserId(userEntity);
            projectRepository.save(entityToSave);
            ProjectEntity responseEntity = projectRepository.findByProjectCode(entity.getProjectCode());
            response.setErrorMessage(Messages.PROJECT_CREATED_SUCCEED);
            projectResponse.add(responseEntity);
            projectResponse.forEach(project ->
                    lightList.add(converter.projectConverter(project))
            );

            response.setResponse(lightList);
            response.setStatusCode(HttpStatus.OK);
            return response;
        }else{
            response.setErrorMessage(Messages.PROJECT_EXIST);
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return response;
        }
    }

    @Override
    public ProjectResponse getProjectsByUserId(int userId) {
        ProjectResponse response = new ProjectResponse();
        UserEntity entity = new UserEntity();
        List<ProjectLightDTO> lightList = new ArrayList<>();

        entity.setId(userId);
        List<ProjectEntity> entityList = projectRepository.findByUserId(entity);

        entityList.forEach(project ->
                lightList.add(converter.projectConverter(project))
        );

        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);
        return response;
    }

    @Override
    public ProjectResponse getAllProjects() {
        List<ProjectLightDTO> lightList = new ArrayList<>();
        List<ProjectEntity> projectList = projectRepository.findAll();
        ProjectResponse response = new ProjectResponse();
        projectList.forEach(projectEntity ->
                lightList.add(converter.projectConverter(projectEntity))
        );
        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);
        return response;
    }
}
