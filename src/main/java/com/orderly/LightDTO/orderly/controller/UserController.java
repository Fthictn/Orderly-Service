package com.orderly.LightDTO.orderly.controller;

import com.orderly.LightDTO.orderly.LightDTO.UserLightDTO;
import com.orderly.LightDTO.orderly.model.UserRequest;
import com.orderly.LightDTO.orderly.response.UserResponse;
import com.orderly.LightDTO.orderly.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@ApiOperation(value = "/orderly/user", tags = "User Controller", notes = "User API")
@RestController
public class UserController {

    private final com.orderly.LightDTO.orderly.service.UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @ApiOperation(value = "Controls user informations", response = com.orderly.LightDTO.orderly.response.UserResponse.class)
    @PostMapping("/login")
    public com.orderly.LightDTO.orderly.response.UserResponse login(@RequestBody UserRequest request) throws Exception {
        return service.userAuthenticater(request);
    }

    @ApiOperation(value = "Get all users", response = com.orderly.LightDTO.orderly.response.UserResponse.class)
    @GetMapping("/getAllUsers")
    public com.orderly.LightDTO.orderly.response.UserResponse getAllUsers(){
        return service.getAllUsers();
    }

    @ApiOperation(value = "Create new user", response = com.orderly.LightDTO.orderly.response.UserResponse.class)
    @PostMapping("/createUser")
    public com.orderly.LightDTO.orderly.response.UserResponse createUser(@RequestBody com.orderly.LightDTO.orderly.LightDTO.UserLightDTO entity){
        return service.createNewUser(entity);
    }

    @ApiOperation(value = "Update user informations", response =  com.orderly.LightDTO.orderly.response.UserResponse.class)
    @PostMapping("updateUser")
    public UserResponse updateUser(@RequestBody UserLightDTO entity){
        return service.updateUser(entity);
    }
}
