package com.orderly.LightDTO.orderly.service;

import com.orderly.LightDTO.orderly.LightDTO.ProjectLightDTO;
import com.orderly.LightDTO.orderly.entity.ProjectEntity;
import com.orderly.LightDTO.orderly.entity.UserEntity;
import com.orderly.LightDTO.orderly.messages.Messages;
import com.orderly.LightDTO.orderly.repository.ProjectRepository;
import com.orderly.LightDTO.orderly.response.ProjectResponse;
import com.orderly.LightDTO.orderly.utils.EntityToDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class.getName());

    private final com.orderly.LightDTO.orderly.repository.ProjectRepository projectRepository;

    private final com.orderly.LightDTO.orderly.utils.EntityToDTOConverter converter;

    private List<com.orderly.LightDTO.orderly.entity.ProjectEntity> projectResponse = new ArrayList<>();

    public ProjectServiceImpl(ProjectRepository projectRepository, EntityToDTOConverter converter) {
        this.projectRepository = projectRepository;
        this.converter = converter;
    }

    @Override
    public com.orderly.LightDTO.orderly.response.ProjectResponse createNewProject(com.orderly.LightDTO.orderly.LightDTO.ProjectLightDTO entity) {
        com.orderly.LightDTO.orderly.response.ProjectResponse response = new com.orderly.LightDTO.orderly.response.ProjectResponse();
        projectResponse = new ArrayList<>();
        List<com.orderly.LightDTO.orderly.LightDTO.ProjectLightDTO> lightList = new ArrayList<>();
        com.orderly.LightDTO.orderly.entity.ProjectEntity existingEntity = projectRepository.findByProjectCode(entity.getProjectCode());
        if(existingEntity == null){
            com.orderly.LightDTO.orderly.entity.ProjectEntity entityToSave = new com.orderly.LightDTO.orderly.entity.ProjectEntity();
            entityToSave.setProjectCode(entity.getProjectCode());
            entityToSave.setProjectName(entity.getProjectName());
            com.orderly.LightDTO.orderly.entity.UserEntity userEntity = new com.orderly.LightDTO.orderly.entity.UserEntity();
            userEntity.setId(entity.getUserEntity().getId());
            entityToSave.setUserId(userEntity);
            projectRepository.save(entityToSave);
            com.orderly.LightDTO.orderly.entity.ProjectEntity responseEntity = projectRepository.findByProjectCode(entity.getProjectCode());
            response.setErrorMessage(com.orderly.LightDTO.orderly.messages.Messages.PROJECT_CREATED_SUCCEED);
            projectResponse.add(responseEntity);
            projectResponse.forEach(project ->
                    lightList.add(converter.projectConverter(project))
            );

            response.setResponse(lightList);
            response.setStatusCode(HttpStatus.OK);
            return response;
        }else{
            response.setErrorMessage(com.orderly.LightDTO.orderly.messages.Messages.PROJECT_EXIST);
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return response;
        }
    }

    @Override
    public com.orderly.LightDTO.orderly.response.ProjectResponse getProjectsByUserId(int userId) {
        com.orderly.LightDTO.orderly.response.ProjectResponse response = new com.orderly.LightDTO.orderly.response.ProjectResponse();
        com.orderly.LightDTO.orderly.entity.UserEntity entity = new UserEntity();
        List<com.orderly.LightDTO.orderly.LightDTO.ProjectLightDTO> lightList = new ArrayList<>();

        entity.setId(userId);
        List<com.orderly.LightDTO.orderly.entity.ProjectEntity> entityList = projectRepository.findByUserId(entity);

        entityList.forEach(project ->
                lightList.add(converter.projectConverter(project))
        );

        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(com.orderly.LightDTO.orderly.messages.Messages.GENERAL_SUCCEED_MESSAGE);
        return response;
    }

    @Override
    public com.orderly.LightDTO.orderly.response.ProjectResponse getAllProjects() {
        List<ProjectLightDTO> lightList = new ArrayList<>();
        List<ProjectEntity> projectList = projectRepository.findAll();
        com.orderly.LightDTO.orderly.response.ProjectResponse response = new ProjectResponse();
        projectList.forEach(projectEntity ->
                lightList.add(converter.projectConverter(projectEntity))
        );
        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);
        return response;
    }
}
