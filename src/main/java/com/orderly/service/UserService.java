package com.orderly.service;

import com.orderly.LightDTO.UserLightDTO;
import com.orderly.model.UserRequest;
import com.orderly.response.UserResponse;
import org.springframework.stereotype.Service;

public interface UserService {
    UserResponse userAuthenticater(UserRequest request) throws Exception;
    UserResponse createNewUser(UserLightDTO entity);
    UserResponse updateUser(UserLightDTO entity);
    UserResponse getAllUsers();
}
