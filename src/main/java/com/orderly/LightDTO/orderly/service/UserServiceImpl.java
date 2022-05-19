package com.orderly.LightDTO.orderly.service;

import com.orderly.LightDTO.orderly.LightDTO.UserLightDTO;
import com.orderly.LightDTO.orderly.entity.UserEntity;
import com.orderly.LightDTO.orderly.messages.Messages;
import com.orderly.LightDTO.orderly.model.UserRequest;
import com.orderly.LightDTO.orderly.repository.UserRepository;
import com.orderly.LightDTO.orderly.response.UserResponse;
import com.orderly.LightDTO.orderly.utils.EntityToDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    private final com.orderly.LightDTO.orderly.utils.EntityToDTOConverter converter;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    private final com.orderly.LightDTO.orderly.repository.UserRepository userRepository;

    private List<com.orderly.LightDTO.orderly.entity.UserEntity> userResponseList = new ArrayList<>();

    public UserServiceImpl(EntityToDTOConverter converter, JwtUtil jwtUtil, AuthenticationManager authenticationManager,
                           CustomUserDetailsService customUserDetailsService, UserRepository userRepository) {
        this.converter = converter;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    public com.orderly.LightDTO.orderly.response.UserResponse userAuthenticater(UserRequest request){
        Authentication authResult = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUserName());

        String token = jwtUtil.generateToken(userDetails);

        List<com.orderly.LightDTO.orderly.entity.UserEntity> userList= userRepository.findUserEntityByUserNameSurnameAndUserPassword(request.getUserName(),request.getPassword());
        userResponseList = new ArrayList<>();
        com.orderly.LightDTO.orderly.response.UserResponse response = new com.orderly.LightDTO.orderly.response.UserResponse();
        List<com.orderly.LightDTO.orderly.LightDTO.UserLightDTO> lightList;
        if(userList != null && !userList.isEmpty() && authResult.isAuthenticated()){
            lightList = new ArrayList<>();
            userResponseList.add(userList.get(0));
            response.setStatusCode(HttpStatus.OK);
            response.setErrorMessage(com.orderly.LightDTO.orderly.messages.Messages.LOGIN_SUCCEED);
            response.setToken(token);
            userResponseList.forEach(user ->
                    lightList.add(converter.userConverter(user))
            );
            response.setResponse(lightList);
        }else {
            response.setErrorMessage(com.orderly.LightDTO.orderly.messages.Messages.CONTROL_LOGIN_INFOS);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
        }
        return response;
    }

    @Override
    public com.orderly.LightDTO.orderly.response.UserResponse createNewUser(com.orderly.LightDTO.orderly.LightDTO.UserLightDTO entity) {
        userResponseList = new ArrayList<>();

        List<com.orderly.LightDTO.orderly.entity.UserEntity> allUsers = userRepository.findAll();
        com.orderly.LightDTO.orderly.response.UserResponse response = userExistingControl(allUsers,entity);
        List<com.orderly.LightDTO.orderly.LightDTO.UserLightDTO> lightList = new ArrayList<>();
        com.orderly.LightDTO.orderly.entity.UserEntity entityToSave = new com.orderly.LightDTO.orderly.entity.UserEntity();
        entityToSave.setIsBanned(entity.getIsBanned());
        entityToSave.setUserEmail(entity.getUserEmail());
        entityToSave.setUserNameSurname(entity.getUserNameSurname());
        entityToSave.setUserPassword(entity.getUserPassword());
        entityToSave.setUserRole(entity.getUserRole());
        entityToSave.setUserTitle(entity.getUserTitle());
        userRepository.save(entityToSave);
        com.orderly.LightDTO.orderly.entity.UserEntity responseEntity = userRepository.findByUserEmail(entity.getUserEmail());
        userResponseList.add(responseEntity);
        userResponseList.forEach(user ->
                lightList.add(converter.userConverter(user))
        );
        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(com.orderly.LightDTO.orderly.messages.Messages.REGISTER_SUCCEED);
        return response;
    }

    private com.orderly.LightDTO.orderly.response.UserResponse userExistingControl(List<com.orderly.LightDTO.orderly.entity.UserEntity> allUsers, com.orderly.LightDTO.orderly.LightDTO.UserLightDTO entity){
        com.orderly.LightDTO.orderly.response.UserResponse response = new com.orderly.LightDTO.orderly.response.UserResponse();
        for (com.orderly.LightDTO.orderly.entity.UserEntity user:allUsers) {
            if (user.getUserEmail().trim().equals(entity.getUserEmail().trim())) {
                response.setErrorMessage(com.orderly.LightDTO.orderly.messages.Messages.EMAIL_EXIST);
                response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                break;
            }
        }
        return response;
    }

    @Override
    public com.orderly.LightDTO.orderly.response.UserResponse updateUser(com.orderly.LightDTO.orderly.LightDTO.UserLightDTO entity) {
        com.orderly.LightDTO.orderly.response.UserResponse response = new com.orderly.LightDTO.orderly.response.UserResponse();
        userResponseList = new ArrayList<>();
        List<com.orderly.LightDTO.orderly.LightDTO.UserLightDTO> lightList = new ArrayList<>();
        com.orderly.LightDTO.orderly.entity.UserEntity existingEntity = userRepository.findById(entity.getId());
        userRepository.save(getExistingEntity(existingEntity,entity));
        userResponseList.add(userRepository.findByUserEmail(existingEntity.getUserEmail()));
        response.setErrorMessage(com.orderly.LightDTO.orderly.messages.Messages.USER_INFOS_UPDATED);
        response.setStatusCode(HttpStatus.OK);
        userResponseList.forEach(user ->
                lightList.add(converter.userConverter(user))
        );
        response.setResponse(lightList);
        return response;
    }

    private com.orderly.LightDTO.orderly.entity.UserEntity getExistingEntity(com.orderly.LightDTO.orderly.entity.UserEntity existingEntity, com.orderly.LightDTO.orderly.LightDTO.UserLightDTO entity){
        if(entity.getUserEmail() != null){
            existingEntity.setUserEmail(entity.getUserEmail());
        }
        if(entity.getIsBanned() != null){
            existingEntity.setIsBanned(entity.getIsBanned());
        }
        if(entity.getUserNameSurname() != null){
            existingEntity.setUserNameSurname(entity.getUserNameSurname());
        }
        if(entity.getUserPassword() != null){
            existingEntity.setUserPassword(entity.getUserPassword());
        }
        if(entity.getUserRole() != null){
            existingEntity.setUserRole(entity.getUserRole());
        }
        if(entity.getUserTitle() != null){
            existingEntity.setUserTitle(entity.getUserTitle());
        }

        return existingEntity;
    }

    @Override
    public com.orderly.LightDTO.orderly.response.UserResponse getAllUsers() {
        List<UserLightDTO> lightList = new ArrayList<>();
        List<UserEntity> userList = userRepository.findAll();
        com.orderly.LightDTO.orderly.response.UserResponse response = new UserResponse();
        userList.forEach(user ->
                lightList.add(converter.userConverter(user))
        );
        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);
        return response;
    }
}
