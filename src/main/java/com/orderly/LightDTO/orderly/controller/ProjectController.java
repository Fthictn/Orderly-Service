package com.orderly.LightDTO.orderly.controller;

import com.orderly.LightDTO.orderly.LightDTO.ProjectLightDTO;
import com.orderly.LightDTO.orderly.response.ProjectResponse;
import com.orderly.LightDTO.orderly.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@ApiOperation(value = "/orderly/project", tags = "Project Controller", notes = "Project API")
@RestController
public class ProjectController {

    private final com.orderly.LightDTO.orderly.service.ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @ApiOperation(value = "Get all Projects", response = com.orderly.LightDTO.orderly.response.ProjectResponse.class)
    @GetMapping("/getAllProjects")
    public com.orderly.LightDTO.orderly.response.ProjectResponse getAllProjects(){
        return service.getAllProjects();
    }


    @ApiOperation(value = "Create new project", response = com.orderly.LightDTO.orderly.response.ProjectResponse.class)
    @PostMapping("/createProject")
    public com.orderly.LightDTO.orderly.response.ProjectResponse createProject(@RequestBody ProjectLightDTO entity){
        return service.createNewProject(entity);
    }

    @ApiOperation(value = "Get project by user id", response = com.orderly.LightDTO.orderly.response.ProjectResponse.class)
    @PostMapping("/users/{userId}/projects")
    public ProjectResponse getAllProjectsByUserId(@PathVariable(value = "userId") int userId){
        return service.getProjectsByUserId(userId);
    }
}
