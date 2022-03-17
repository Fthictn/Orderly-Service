package com.orderly.service;

import com.orderly.LightDTO.PostLightDTO;
import com.orderly.response.PostResponse;
import org.springframework.stereotype.Service;

public interface PostService {
    PostResponse getPostsByProjectId(int projectId);
    PostResponse getAllPosts();
    PostResponse getPostsByProjectCode(String projectCode);
    PostResponse createPost(PostLightDTO entity);
}
