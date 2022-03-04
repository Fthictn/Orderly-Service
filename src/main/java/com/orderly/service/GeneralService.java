package com.orderly.service;

import com.orderly.LightDTO.AnswerLightDTO;
import com.orderly.LightDTO.PostLightDTO;
import com.orderly.LightDTO.ProjectLightDTO;
import com.orderly.LightDTO.UserLightDTO;
import com.orderly.entity.ProjectEntity;
import com.orderly.entity.UserEntity;
import com.orderly.model.UserRequest;
import com.orderly.response.AnswerResponse;
import com.orderly.response.PostResponse;
import com.orderly.response.ProjectResponse;
import com.orderly.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GeneralService {
    UserResponse userAuthenticater(UserRequest request) throws Exception;
    UserResponse CreateNewUser(UserLightDTO entity);
    UserResponse UpdateUser(UserLightDTO entity);
    ProjectResponse CreateNewProject(ProjectLightDTO entity);
    PostResponse getPostsByProjectId(int projectId);
    ProjectResponse getProjectsByUserId(int userId);
    AnswerResponse getAnswersByPostId(int postId);
    PostResponse getAllPosts();
    AnswerResponse getAllAnswers();
    ProjectResponse getAllProjects();
    UserResponse getAllUsers();
    PostResponse getPostsByProjectCode(String projectCode);
    PostResponse CreatePost(PostLightDTO entity);
    AnswerResponse CreateAnswer(AnswerLightDTO entity);
    Boolean sendMail();
}
