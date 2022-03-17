package com.orderly.service;

import com.orderly.LightDTO.PostLightDTO;
import com.orderly.entity.PostEntity;
import com.orderly.entity.ProjectEntity;
import com.orderly.messages.Messages;
import com.orderly.repository.PostRepository;
import com.orderly.repository.ProjectRepository;
import com.orderly.response.PostResponse;
import com.orderly.utils.EntityToDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class.getName());

    private final PostRepository postRepository;

    private final EntityToDTOConverter converter;

    private final ProjectRepository projectRepository;

    public PostServiceImpl(PostRepository postRepository, EntityToDTOConverter converter, ProjectRepository projectRepository) {
        this.postRepository = postRepository;
        this.converter = converter;
        this.projectRepository = projectRepository;
    }

    @Override
    public PostResponse getPostsByProjectId(int projectId) {
        PostResponse response = new PostResponse();
        ProjectEntity entity = new ProjectEntity();
        List<PostLightDTO> lightList = new ArrayList<>();

        entity.setId(projectId);
        List<PostEntity> entityList = postRepository.findByProjectId(entity);

        entityList.forEach(post ->
                lightList.add(converter.postConverter(post))
        );

        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);

        return response;
    }

    @Override
    public PostResponse getAllPosts() {
        List<PostLightDTO> lightList = new ArrayList<>();
        List<PostEntity> postList = postRepository.findAll();
        PostResponse response = new PostResponse();
        postList.forEach(post ->
                lightList.add(converter.postConverter(post))
        );
        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);
        return response;
    }

    @Override
    public PostResponse getPostsByProjectCode(String projectCode) {
        ProjectEntity projectEntity = projectRepository.findByProjectCode(projectCode);
        List<PostEntity> postList = postRepository.findByProjectId(projectEntity);
        List<PostLightDTO> lightList = new ArrayList<>();
        PostResponse response = new PostResponse();
        postList.forEach(post ->
                lightList.add(converter.postConverter(post))
        );
        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);
        return response;
    }

    @Override
    public PostResponse createPost(PostLightDTO entity) {
        PostResponse response = new PostResponse();
        postRepository.save(getEntityToSave(entity));
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);
        response.setStatusCode(HttpStatus.OK);
        return response;
    }

    private PostEntity getEntityToSave(PostLightDTO entity){
        PostEntity entityToSave = new PostEntity();
        entityToSave.setPostTitle(entity.getPostTitle());
        entityToSave.setPostContent(entity.getPostContent());
        entityToSave.setType(entity.getType());
        entityToSave.setCreatedTime(LocalDateTime.now().toString());
        entityToSave.setIsSolved("0");
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(entity.getProjectEntity().getId());
        entityToSave.setProjectId(projectEntity);
        return entityToSave;
    }
}
