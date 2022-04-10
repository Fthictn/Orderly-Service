package com.orderly.controller;

import com.orderly.LightDTO.UserLightDTO;
import com.orderly.model.UserRequest;
import com.orderly.response.UserResponse;
import com.orderly.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@ApiOperation(value = "/orderly/user", tags = "User Controller", notes = "User API")
@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @ApiOperation(value = "Controls user informations", response = UserResponse.class)
    @PostMapping("/login")
    public UserResponse login(@RequestBody UserRequest request) throws Exception {
        return service.userAuthenticater(request);
    }

    @ApiOperation(value = "Get all users", response = UserResponse.class)
    @GetMapping("/getAllUsers")
    public UserResponse getAllUsers(){
        return service.getAllUsers();
    }

    @ApiOperation(value = "Create new user", response = UserResponse.class)
    @PostMapping("/createUser")
    public UserResponse createUser(@RequestBody UserLightDTO entity){
        return service.createNewUser(entity);
    }

    @ApiOperation(value = "Update user informations", response =  UserResponse.class)
    @PostMapping("updateUser")
    public UserResponse updateUser(@RequestBody UserLightDTO entity){
        return service.updateUser(entity);
    }
}
