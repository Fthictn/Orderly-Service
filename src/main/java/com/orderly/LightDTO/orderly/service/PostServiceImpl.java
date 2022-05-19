package com.orderly.LightDTO.orderly.service;

import com.orderly.LightDTO.orderly.LightDTO.PostLightDTO;
import com.orderly.LightDTO.orderly.entity.PostEntity;
import com.orderly.LightDTO.orderly.entity.ProjectEntity;
import com.orderly.LightDTO.orderly.messages.Messages;
import com.orderly.LightDTO.orderly.repository.PostRepository;
import com.orderly.LightDTO.orderly.repository.ProjectRepository;
import com.orderly.LightDTO.orderly.response.PostResponse;
import com.orderly.LightDTO.orderly.utils.EntityToDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class.getName());

    private final com.orderly.LightDTO.orderly.repository.PostRepository postRepository;

    private final com.orderly.LightDTO.orderly.utils.EntityToDTOConverter converter;

    private final com.orderly.LightDTO.orderly.repository.ProjectRepository projectRepository;

    public PostServiceImpl(PostRepository postRepository, EntityToDTOConverter converter, ProjectRepository projectRepository) {
        this.postRepository = postRepository;
        this.converter = converter;
        this.projectRepository = projectRepository;
    }

    @Override
    public com.orderly.LightDTO.orderly.response.PostResponse getPostsByProjectId(int projectId) {
        com.orderly.LightDTO.orderly.response.PostResponse response = new com.orderly.LightDTO.orderly.response.PostResponse();
        com.orderly.LightDTO.orderly.entity.ProjectEntity entity = new com.orderly.LightDTO.orderly.entity.ProjectEntity();
        List<com.orderly.LightDTO.orderly.LightDTO.PostLightDTO> lightList = new ArrayList<>();

        entity.setId(projectId);
        List<com.orderly.LightDTO.orderly.entity.PostEntity> entityList = postRepository.findByProjectId(entity);

        entityList.forEach(post ->
                lightList.add(converter.postConverter(post))
        );

        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(com.orderly.LightDTO.orderly.messages.Messages.GENERAL_SUCCEED_MESSAGE);

        return response;
    }

    @Override
    public com.orderly.LightDTO.orderly.response.PostResponse getAllPosts() {
        List<com.orderly.LightDTO.orderly.LightDTO.PostLightDTO> lightList = new ArrayList<>();
        List<com.orderly.LightDTO.orderly.entity.PostEntity> postList = postRepository.findAll();
        com.orderly.LightDTO.orderly.response.PostResponse response = new com.orderly.LightDTO.orderly.response.PostResponse();
        postList.forEach(post ->
                lightList.add(converter.postConverter(post))
        );
        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(com.orderly.LightDTO.orderly.messages.Messages.GENERAL_SUCCEED_MESSAGE);
        return response;
    }

    @Override
    public com.orderly.LightDTO.orderly.response.PostResponse getPostsByProjectCode(String projectCode) {
        com.orderly.LightDTO.orderly.entity.ProjectEntity projectEntity = projectRepository.findByProjectCode(projectCode);
        List<com.orderly.LightDTO.orderly.entity.PostEntity> postList = postRepository.findByProjectId(projectEntity);
        List<com.orderly.LightDTO.orderly.LightDTO.PostLightDTO> lightList = new ArrayList<>();
        com.orderly.LightDTO.orderly.response.PostResponse response = new com.orderly.LightDTO.orderly.response.PostResponse();
        postList.forEach(post ->
                lightList.add(converter.postConverter(post))
        );
        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(com.orderly.LightDTO.orderly.messages.Messages.GENERAL_SUCCEED_MESSAGE);
        return response;
    }

    @Override
    public com.orderly.LightDTO.orderly.response.PostResponse createPost(com.orderly.LightDTO.orderly.LightDTO.PostLightDTO entity) {
        com.orderly.LightDTO.orderly.response.PostResponse response = new PostResponse();
        postRepository.save(getEntityToSave(entity));
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);
        response.setStatusCode(HttpStatus.OK);
        return response;
    }

    private com.orderly.LightDTO.orderly.entity.PostEntity getEntityToSave(PostLightDTO entity){
        com.orderly.LightDTO.orderly.entity.PostEntity entityToSave = new PostEntity();
        entityToSave.setPostTitle(entity.getPostTitle());
        entityToSave.setPostContent(entity.getPostContent());
        entityToSave.setType(entity.getType());
        entityToSave.setCreatedTime(LocalDateTime.now().toString());
        entityToSave.setIsSolved("0");
        com.orderly.LightDTO.orderly.entity.ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(entity.getProjectEntity().getId());
        entityToSave.setProjectId(projectEntity);
        return entityToSave;
    }
}
