package com.orderly.service;

import com.orderly.LightDTO.AnswerLightDTO;
import com.orderly.LightDTO.PostLightDTO;
import com.orderly.LightDTO.ProjectLightDTO;
import com.orderly.LightDTO.UserLightDTO;
import com.orderly.entity.AnswerEntity;
import com.orderly.entity.PostEntity;
import com.orderly.entity.ProjectEntity;
import com.orderly.entity.UserEntity;
import com.orderly.messages.Messages;
import com.orderly.repository.AnswerRepository;
import com.orderly.repository.PostRepository;
import com.orderly.repository.ProjectRepository;
import com.orderly.repository.UserRepository;
import com.orderly.model.UserRequest;
import com.orderly.response.AnswerResponse;
import com.orderly.response.PostResponse;
import com.orderly.response.ProjectResponse;
import com.orderly.response.UserResponse;
import com.orderly.security.CustomUserDetailsService;
import com.orderly.security.JwtUtil;
import com.orderly.utils.EntityToDTOConverter;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service("generalService")
public class GeneralServiceImpl implements GeneralService {

    private final Logger log = LoggerFactory.getLogger(GeneralServiceImpl.class.getName());

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    private final PostRepository postRepository;

    private final AnswerRepository answerRepository;

    private List<UserEntity> userResponse = new ArrayList<>();

    private List<ProjectEntity> projectResponse = new ArrayList<>();

    private final EntityToDTOConverter converter;

    private final JavaMailSender mailSender;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    CustomUserDetailsService customUserDetailsService;
    @Autowired
    GeneralServiceImpl(UserRepository userRepository,ProjectRepository projectRepository,PostRepository postRepository,AnswerRepository answerRepository,
                       EntityToDTOConverter converter,JavaMailSender mailSender, JwtUtil jwtUtil, AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService){
        this.userRepository=userRepository;
        this.projectRepository = projectRepository;
        this.postRepository = postRepository;
        this.answerRepository = answerRepository;
        this.converter = converter;
        this.mailSender = mailSender;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
    }
    @Override
    public UserResponse userAuthenticater(UserRequest request) throws Exception {

        Authentication authResult = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUserName());

        String token = jwtUtil.generateToken(userDetails);

        List<UserEntity> userList= userRepository.findUserEntityByUserNameSurnameAndUserPassword(request.getUserName(),request.getPassword());
        userResponse = new ArrayList<>();
        UserResponse response = new UserResponse();
        List<UserLightDTO> lightList;
        if(userList != null && !userList.isEmpty() && authResult.isAuthenticated()){
            lightList = new ArrayList<>();
            userResponse.add(userList.get(0));
            response.setStatusCode(HttpStatus.OK);
            response.setErrorMessage(Messages.LOGIN_SUCCEED);
            response.setToken(token);
            userResponse.forEach(user ->
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
    public UserResponse CreateNewUser(UserLightDTO entity) {
        boolean isValid = true;
        userResponse = new ArrayList<>();
        UserResponse response = new UserResponse();
        List<UserEntity> allUsers = (List<UserEntity>) userRepository.findAll();
        for (UserEntity user:allUsers) {
            if (user.getUserEmail().trim().equals(entity.getUserEmail().trim())) {
                response.setErrorMessage(Messages.EMAIL_EXIST);
                response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                isValid = true;
                break;
            }
        }
        if(isValid){
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
            userResponse.add(responseEntity);

            userResponse.forEach(user ->
                    lightList.add(converter.userConverter(user))
            );
            response.setResponse(lightList);
            response.setStatusCode(HttpStatus.OK);
            response.setErrorMessage(Messages.REGISTER_SUCCEED);
            return response;
        }else {
            return response;
        }
    }

    @Override
    public UserResponse UpdateUser(UserLightDTO entity) {
            UserResponse response = new UserResponse();
            userResponse = new ArrayList<>();
            List<UserLightDTO> lightList = new ArrayList<>();
            UserEntity existingEntity = userRepository.findById(entity.getId());

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

           userRepository.save(existingEntity);
           userResponse.add(userRepository.findByUserEmail(existingEntity.getUserEmail()));
           response.setErrorMessage(Messages.USER_INFOS_UPDATED);
           response.setStatusCode(HttpStatus.OK);

            userResponse.forEach(user ->
                    lightList.add(converter.userConverter(user))
            );


           response.setResponse(lightList);
           return response;
    }

    @Override
    public ProjectResponse CreateNewProject(ProjectLightDTO entity) {
        ProjectResponse response = new ProjectResponse();
        projectResponse = new ArrayList<>();
        List<ProjectLightDTO> lightList = new ArrayList<>();
        ProjectEntity existingEntity = projectRepository.findByProjectCode(entity.getProjectCode());
        if(existingEntity == null){
            ProjectEntity entityToSave = new ProjectEntity();
            entityToSave.setProjectCode(entity.getProjectCode());
            entityToSave.setProjectName(entity.getProjectName());
            UserEntity userEntity = new UserEntity();
            userEntity.setId(entity.getUserEntity().getId());
            entityToSave.setUserId(userEntity);
            projectRepository.save(entityToSave);
            ProjectEntity responseEntity = projectRepository.findByProjectCode(entity.getProjectCode());
            response.setErrorMessage(Messages.PROJECT_CREATED_SUCCEED);
            projectResponse.add(responseEntity);
            projectResponse.forEach(project ->
                    lightList.add(converter.projectConverter(project))
            );

            response.setResponse(lightList);
            response.setStatusCode(HttpStatus.OK);
            return response;
        }else{
            response.setErrorMessage(Messages.PROJECT_EXIST);
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return response;
        }
    }

    @Override
    public PostResponse getPostsByProjectId(int projectId) {
        PostResponse response = new PostResponse();
        ProjectEntity entity = new ProjectEntity();
        List<PostLightDTO> lightList = new ArrayList<>();

        entity.setId(projectId);
        List<PostEntity> entityList = postRepository.findByProjectId(entity);

        entityList.forEach(post ->
                lightList.add(converter.postConverter(post))
        );

        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);

        return response;
    }

    @Override
    public ProjectResponse getProjectsByUserId(int userId) {
        ProjectResponse response = new ProjectResponse();
        UserEntity entity = new UserEntity();
        List<ProjectLightDTO> lightList = new ArrayList<>();

        entity.setId(userId);
        List<ProjectEntity> entityList = projectRepository.findByUserId(entity);

        entityList.forEach(project ->
                lightList.add(converter.projectConverter(project))
        );

        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);
        return response;
    }

    @Override
    public AnswerResponse getAnswersByPostId(int postId) {
        AnswerResponse response = new AnswerResponse();
        PostEntity entity = new PostEntity();
        List<AnswerLightDTO> lightList = new ArrayList<>();

        entity.setId(postId);
        List<AnswerEntity> entityList = answerRepository.findByPostId(entity);

        entityList.forEach(answer ->
                lightList.add(converter.answerConverter(answer))
        );
        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);
        return response;
    }

    @Override
    public PostResponse getAllPosts() {
        List<PostLightDTO> lightList = new ArrayList<>();
        List<PostEntity> postList = postRepository.findAll();
        PostResponse response = new PostResponse();

        postList.forEach(post ->
                lightList.add(converter.postConverter(post))
        );

        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);

        return response;
    }

    @Override
    public AnswerResponse getAllAnswers() {
        List<AnswerLightDTO> lightList = new ArrayList<>();
        List<AnswerEntity> answerList = answerRepository.findAll();
        AnswerResponse response = new AnswerResponse();

        answerList.forEach(answer ->
                lightList.add(converter.answerConverter(answer))
        );

        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);

        return response;
    }

    @Override
    public ProjectResponse getAllProjects() {
        List<ProjectLightDTO> lightList = new ArrayList<>();
        List<ProjectEntity> projectList = projectRepository.findAll();
        ProjectResponse response = new ProjectResponse();

        projectList.forEach(projectEntity ->
                lightList.add(converter.projectConverter(projectEntity))
        );

        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);

        return response;
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

    @Override
    public PostResponse getPostsByProjectCode(String projectCode) {
        ProjectEntity projectEntity = projectRepository.findByProjectCode(projectCode);
        List<PostEntity> postList = postRepository.findByProjectId(projectEntity);
        List<PostLightDTO> lightList = new ArrayList<>();
        PostResponse response = new PostResponse();

        postList.forEach(post ->
                lightList.add(converter.postConverter(post))
        );

        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);

        return response;
    }

    @Override
    public PostResponse CreatePost(PostLightDTO entity) {
        PostResponse response = new PostResponse();
        PostEntity entityToSave = new PostEntity();
        entityToSave.setPostTitle(entity.getPostTitle());
        entityToSave.setPostContent(entity.getPostContent());
        entityToSave.setType(entity.getType());
        entityToSave.setCreatedTime(LocalDateTime.now().toString());
        entityToSave.setIsSolved("0");
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(entity.getProjectEntity().getId());
        entityToSave.setProjectId(projectEntity);
        postRepository.save(entityToSave);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);
        response.setStatusCode(HttpStatus.OK);
        return response;
    }

    @Override
    public AnswerResponse CreateAnswer(AnswerLightDTO entity) {
        AnswerResponse response = new AnswerResponse();
        AnswerEntity entityToSave = new AnswerEntity();
        entityToSave.setContent(entity.getContent());
        entityToSave.setCreatedTime(LocalDateTime.now().toString());
        entityToSave.setIsCorrect("0");
        PostEntity postEntity = new PostEntity();
        postEntity.setId(entity.getPostEntity().getId());
        entityToSave.setPostId(postEntity);
        answerRepository.save(entityToSave);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);
        response.setStatusCode(HttpStatus.OK);
        return response;
    }

    @Override
    public Boolean sendMail() {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        try {
            messageHelper.setTo("fthi.cetin@gmail.com");
            messageHelper.setText("Üyeliğiniz oluşturulmuştur.");
            messageHelper.setSubject("Orderly Üyelik Oluşturma");
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        mailSender.send(mimeMessage);
        return true;
    }

}
