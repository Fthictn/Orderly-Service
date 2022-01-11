package com.orderly.controller;

import com.orderly.LightDTO.AnswerLightDTO;
import com.orderly.LightDTO.PostLightDTO;
import com.orderly.LightDTO.ProjectLightDTO;
import com.orderly.LightDTO.UserLightDTO;
import com.orderly.entity.AnswerEntity;
import com.orderly.entity.PostEntity;
import com.orderly.entity.ProjectEntity;
import com.orderly.entity.UserEntity;
import com.orderly.model.UserRequest;
import com.orderly.repository.AnswerRepository;
import com.orderly.repository.PostRepository;
import com.orderly.repository.ProjectRepository;
import com.orderly.repository.UserRepository;
import com.orderly.response.AnswerResponse;
import com.orderly.response.PostResponse;
import com.orderly.response.ProjectResponse;
import com.orderly.response.UserResponse;
import com.orderly.service.GeneralService;
import com.orderly.utils.EntityToDTOConverter;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.*;

@ApiOperation(value = "/orderly/v1/api", tags = "Orderly Controller", notes = "Orderly API")
@RequestMapping("/orderly/v1/api")
@RestController
public class OrderlyController {

    public UserRepository userRepo;

    public PostRepository postRepo;

    public AnswerRepository answerRepo;

    public GeneralService service;

    public ProjectRepository projectRepo;

    EntityToDTOConverter converter;

    @Autowired
    public OrderlyController(UserRepository userRepo, PostRepository postRepo, AnswerRepository answerRepo, @Qualifier("generalService") GeneralService service, ProjectRepository projectRepo,
                             EntityToDTOConverter converter) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.answerRepo = answerRepo;
        this.service = service;
        this.projectRepo = projectRepo;
        this.converter = converter;
    }

    public OrderlyController() {

    }

    @ApiOperation(value = "Get all posts", response = PostResponse.class)
    @GetMapping("/getAllPosts")
    public PostResponse getAllPosts(){
        return service.getAllPosts();
    }

    @ApiOperation(value = "Get all answers ", response = AnswerResponse.class)
    @GetMapping("/getAllAnswers")
    public AnswerResponse getAllAnswers(){
        return service.getAllAnswers();
    }

    @ApiOperation(value = "Get all users", response = UserResponse.class)
    @GetMapping("/getAllUsers")
    public UserResponse getAllUsers(){
        return service.getAllUsers();
    }

    @ApiOperation(value = "Get all Projects", response = ProjectResponse.class)
    @GetMapping("/getAllProjects")
    public ProjectResponse getAllProjects(){
        return service.getAllProjects();
    }

    @ApiOperation(value = "Controls user informations", response = UserResponse.class)
    @PostMapping("/authonticate")
    public UserResponse authanticateUser(@RequestBody UserRequest request){
        return service.UserAuthanticater(request);
    }

    @ApiOperation(value = "Create new user", response = UserResponse.class)
    @PostMapping("/createUser")
    public UserResponse createUser(@RequestBody UserLightDTO entity){
            return service.CreateNewUser(entity);
    }

    @ApiOperation(value = "Create new project", response = ProjectResponse.class)
    @PostMapping("/createProject")
    public ProjectResponse createProject(@RequestBody ProjectLightDTO entity){
        return service.CreateNewProject(entity);
    }
    @ApiOperation(value = "Add post", response = PostResponse.class)
    @PostMapping("/createPost")
    public PostResponse createPost(@RequestBody PostLightDTO entity){
        return service.CreatePost(entity);
    }

    @ApiOperation(value = "Add answer", response = AnswerResponse.class)
    @PostMapping("/createAnswer")
    public AnswerResponse createAnswer(@RequestBody AnswerLightDTO entity){
        return service.CreateAnswer(entity);
    }

    @ApiOperation(value = "Update user informations", response =  UserResponse.class)
    @PostMapping("updateUser")
    public UserResponse updateUser(@RequestBody UserLightDTO entity){
        return service.UpdateUser(entity);
    }

    @ApiOperation(value = "Get post by project id", response = PostResponse.class)
    @PostMapping("/projects/{projectId}/posts")
    public PostResponse getAllPostByProjectId(@PathVariable (value = "projectId") int projectId){
        return service.getPostsByProjectId(projectId);
    }

    @ApiOperation(value = "Get project by user id", response = ProjectResponse.class)
    @PostMapping("/users/{userId}/projects")
    public ProjectResponse getAllProjectsByUserId(@PathVariable (value = "userId") int userId){
        return service.getProjectsByUserId(userId);
    }

    @ApiOperation(value = "Get answer by post id", response = AnswerResponse.class)
    @PostMapping("/posts/{postId}/answers")
    public AnswerResponse getAllAnswersByPostId(@PathVariable (value = "postId") int postId){
        return service.getAnswersByPostId(postId);
    }

    @ApiOperation(value = "Get post by project code", response = PostResponse.class)
    @GetMapping("/projects/{projectCode}")
    public PostResponse getPostsByProjectCode(@PathVariable (value = "projectCode") String projectCode){
        return service.getPostsByProjectCode(projectCode);
    }
//@PathVariable (value = "email") String email
    @ApiOperation(value = "Send Mail", response = Boolean.class)
    @GetMapping("/sendMail")
    public Boolean sendMail(){
        return service.sendMail();
    }

}