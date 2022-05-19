package com.orderly.LightDTO.orderly.service;

import com.orderly.LightDTO.orderly.LightDTO.PostLightDTO;
import com.orderly.LightDTO.orderly.response.PostResponse;
import org.springframework.stereotype.Service;

public interface PostService {
    com.orderly.LightDTO.orderly.response.PostResponse getPostsByProjectId(int projectId);
    com.orderly.LightDTO.orderly.response.PostResponse getAllPosts();
    com.orderly.LightDTO.orderly.response.PostResponse getPostsByProjectCode(String projectCode);
    PostResponse createPost(PostLightDTO entity);
}
