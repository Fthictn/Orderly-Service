package com.orderly.LightDTO.orderly.service;

import com.orderly.LightDTO.orderly.LightDTO.UserLightDTO;
import com.orderly.LightDTO.orderly.model.UserRequest;
import com.orderly.LightDTO.orderly.response.UserResponse;
import org.springframework.stereotype.Service;

public interface UserService {
    com.orderly.LightDTO.orderly.response.UserResponse userAuthenticater(UserRequest request) throws Exception;
    com.orderly.LightDTO.orderly.response.UserResponse createNewUser(com.orderly.LightDTO.orderly.LightDTO.UserLightDTO entity);
    com.orderly.LightDTO.orderly.response.UserResponse updateUser(UserLightDTO entity);
    UserResponse getAllUsers();
}
