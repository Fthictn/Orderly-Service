package com.orderly.LightDTO.orderly.service;

import com.orderly.LightDTO.orderly.LightDTO.AnswerLightDTO;
import com.orderly.LightDTO.orderly.entity.AnswerEntity;
import com.orderly.LightDTO.orderly.entity.PostEntity;
import com.orderly.LightDTO.orderly.messages.Messages;
import com.orderly.LightDTO.orderly.repository.AnswerRepository;
import com.orderly.LightDTO.orderly.response.AnswerResponse;
import com.orderly.LightDTO.orderly.utils.EntityToDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final Logger log = LoggerFactory.getLogger(AnswerServiceImpl.class.getName());

    private final com.orderly.LightDTO.orderly.repository.AnswerRepository answerRepository;

    private final com.orderly.LightDTO.orderly.utils.EntityToDTOConverter converter;

    public AnswerServiceImpl(AnswerRepository answerRepository, EntityToDTOConverter converter) {
        this.answerRepository = answerRepository;
        this.converter = converter;
    }

    @Override
    public com.orderly.LightDTO.orderly.response.AnswerResponse getAnswersByPostId(int postId) {
        com.orderly.LightDTO.orderly.response.AnswerResponse response = new com.orderly.LightDTO.orderly.response.AnswerResponse();
        com.orderly.LightDTO.orderly.entity.PostEntity entity = new com.orderly.LightDTO.orderly.entity.PostEntity();
        List<com.orderly.LightDTO.orderly.LightDTO.AnswerLightDTO> lightList = new ArrayList<>();
        entity.setId(postId);
        List<com.orderly.LightDTO.orderly.entity.AnswerEntity> entityList = answerRepository.findByPostId(entity);

        entityList.forEach(answer ->
                lightList.add(converter.answerConverter(answer))
        );
        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(com.orderly.LightDTO.orderly.messages.Messages.GENERAL_SUCCEED_MESSAGE);
        return response;
    }

    @Override
    public com.orderly.LightDTO.orderly.response.AnswerResponse getAllAnswers() {
        List<com.orderly.LightDTO.orderly.LightDTO.AnswerLightDTO> lightList = new ArrayList<>();
        List<com.orderly.LightDTO.orderly.entity.AnswerEntity> answerList = answerRepository.findAll();
        com.orderly.LightDTO.orderly.response.AnswerResponse response = new com.orderly.LightDTO.orderly.response.AnswerResponse();
        answerList.forEach(answer ->
                lightList.add(converter.answerConverter(answer))
        );
        response.setResponse(lightList);
        response.setStatusCode(HttpStatus.OK);
        response.setErrorMessage(com.orderly.LightDTO.orderly.messages.Messages.GENERAL_SUCCEED_MESSAGE);
        return response;
    }

    @Override
    public com.orderly.LightDTO.orderly.response.AnswerResponse createAnswer(AnswerLightDTO entity) {
        com.orderly.LightDTO.orderly.response.AnswerResponse response = new AnswerResponse();
        com.orderly.LightDTO.orderly.entity.AnswerEntity entityToSave = new com.orderly.LightDTO.orderly.entity.AnswerEntity();
        entityToSave.setContent(entity.getContent());
        entityToSave.setCreatedTime(LocalDateTime.now().toString());
        entityToSave.setIsCorrect("0");
        com.orderly.LightDTO.orderly.entity.PostEntity postEntity = new PostEntity();
        postEntity.setId(entity.getPostEntity().getId());
        entityToSave.setPostId(postEntity);
        AnswerEntity savedEntity = answerRepository.save(entityToSave);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);
        response.setStatusCode(HttpStatus.OK);
        response.setAnswerEntity(savedEntity);
        return response;
    }
}
