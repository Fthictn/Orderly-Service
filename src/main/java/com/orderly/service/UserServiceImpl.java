package com.orderly.service;

import com.orderly.LightDTO.UserLightDTO;
import com.orderly.entity.UserEntity;
import com.orderly.messages.Messages;
import com.orderly.model.UserRequest;
import com.orderly.repository.UserRepository;
import com.orderly.response.UserResponse;
import com.orderly.security.CustomUserDetailsService;
import com.orderly.security.JwtUtil;
import com.orderly.utils.EntityToDTOConverter;
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
public class UserServiceImpl implements UserService{

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    private final EntityToDTOConverter converter;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    private final UserRepository userRepository;

    private List<UserEntity> userResponseList = new ArrayList<>();

    public UserServiceImpl(EntityToDTOConverter converter, JwtUtil jwtUtil, AuthenticationManager authenticationManager,
                           CustomUserDetailsService customUserDetailsService, UserRepository userRepository) {
        this.converter = converter;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse userAuthenticater(UserRequest request){
        Authentication authResult = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUserName());

        String token = jwtUtil.generateToken(userDetails);

        List<UserEntity> userList= userRepository.findUserEntityByUserNameSurnameAndUserPassword(request.getUserName(),request.getPassword());
        userResponseList = new ArrayList<>();
        UserResponse response = new UserResponse();
        List<UserLightDTO> lightList;
        if(userList != null && !userList.isEmpty() && authResult.isAuthenticated()){
            lightList = new ArrayList<>();
            userResponseList.add(userList.get(0));
            response.setStatusCode(HttpStatus.OK);
            response.setErrorMessage(Messages.LOGIN_SUCCEED);
            response.setToken(token);
            userResponseList.forEach(user ->
                    lightList.add(converter.userConverter(user))
            );
            response.setResponse(lightList);
        }else {
            response.setErrorMessage(Messages.CONTROL_LOGIN_INFOS);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
        }
        return response;
    }

    @Override
    public UserResponse createNewUser(UserLightDTO entity) {
        userResponseList = new ArrayList<>();

        List<UserEntity> allUsers = userRepository.findAll();
        UserResponse response = userExistingControl(allUsers,entity);
        List<UserLightDTO> lightList = new ArrayList<>();
        UserEntity entityToSave = new UserEntity();
        entityToSave.setIsBanned(entity.getIsBanned());
        entityToSave.setUserEmail(entity.getUserEmail());
        entityToSave.setUserNameSurname(entity.getUserNameSurname());
        entityToSave.setUserPassword(entity.getUserPassword());
        entityToSave.setUserRole(entity.getUserRole());
        entityToSave.setUserTitle(entity.getUserTitle());
        userRepository.save(entityToSave);
        UserEntity responseEntity = userRepository.findByUserEmail(entity.getUserEmail());
        userResponseList.add(responseEntity);
        userResponseList.forEach(user ->
                lightList.add(converter.userConverter(user))
        );
        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.REGISTER_SUCCEED);
        return response;
    }

    private UserResponse userExistingControl(List<UserEntity> allUsers, UserLightDTO entity){
        UserResponse response = new UserResponse();
        for (UserEntity user:allUsers) {
            if (user.getUserEmail().trim().equals(entity.getUserEmail().trim())) {
                response.setErrorMessage(Messages.EMAIL_EXIST);
                response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                break;
            }
        }
        return response;
    }

    @Override
    public UserResponse updateUser(UserLightDTO entity) {
        UserResponse response = new UserResponse();
        userResponseList = new ArrayList<>();
        List<UserLightDTO> lightList = new ArrayList<>();
        UserEntity existingEntity = userRepository.findById(entity.getId());
        userRepository.save(getExistingEntity(existingEntity,entity));
        userResponseList.add(userRepository.findByUserEmail(existingEntity.getUserEmail()));
        response.setErrorMessage(Messages.USER_INFOS_UPDATED);
        response.setStatusCode(HttpStatus.OK);
        userResponseList.forEach(user ->
                lightList.add(converter.userConverter(user))
        );
        response.setResponse(lightList);
        return response;
    }

    private UserEntity getExistingEntity(UserEntity existingEntity, UserLightDTO entity){
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
    public UserResponse getAllUsers() {
        List<UserLightDTO> lightList = new ArrayList<>();
        List<UserEntity> userList = userRepository.findAll();
        UserResponse response = new UserResponse();
        userList.forEach(user ->
                lightList.add(converter.userConverter(user))
        );
        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);
        return response;
    }
}
