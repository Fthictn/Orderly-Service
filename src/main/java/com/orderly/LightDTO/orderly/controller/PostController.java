package com.orderly.LightDTO.orderly.controller;

import com.orderly.LightDTO.PostLightDTO;
import com.orderly.response.PostResponse;
import com.orderly.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@ApiOperation(value = "/orderly/post", tags = "Post Controller", notes = "Post API")
@RestController
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @ApiOperation(value = "Get all posts", response = PostResponse.class)
    @GetMapping("/getAllPosts")
    public PostResponse getAllPosts(){
        return service.getAllPosts();
    }

    @ApiOperation(value = "Add post", response = PostResponse.class)
    @PostMapping("/createPost")
    public PostResponse createPost(@RequestBody PostLightDTO entity){
        return service.createPost(entity);
    }

    @ApiOperation(value = "Get post by project id", response = PostResponse.class)
    @PostMapping("/projects/{projectId}/posts")
    public PostResponse getAllPostByProjectId(@PathVariable(value = "projectId") int projectId){
        return service.getPostsByProjectId(projectId);
    }

    @ApiOperation(value = "Get post by project code", response = PostResponse.class)
    @GetMapping("/projects/{projectCode}")
    public PostResponse getPostsByProjectCode(@PathVariable (value = "projectCode") String projectCode){
        return service.getPostsByProjectCode(projectCode);
    }
}
