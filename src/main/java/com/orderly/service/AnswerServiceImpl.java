package com.orderly.service;

import com.orderly.LightDTO.AnswerLightDTO;
import com.orderly.entity.AnswerEntity;
import com.orderly.entity.PostEntity;
import com.orderly.messages.Messages;
import com.orderly.repository.AnswerRepository;
import com.orderly.response.AnswerResponse;
import com.orderly.utils.EntityToDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{

    private final Logger log = LoggerFactory.getLogger(AnswerServiceImpl.class.getName());

    private final AnswerRepository answerRepository;

    private final EntityToDTOConverter converter;

    public AnswerServiceImpl(AnswerRepository answerRepository, EntityToDTOConverter converter) {
        this.answerRepository = answerRepository;
        this.converter = converter;
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
    public AnswerResponse createAnswer(AnswerLightDTO entity) {
        AnswerResponse response = new AnswerResponse();
        AnswerEntity entityToSave = new AnswerEntity();
        entityToSave.setContent(entity.getContent());
        entityToSave.setCreatedTime(LocalDateTime.now().toString());
        entityToSave.setIsCorrect("0");
        PostEntity postEntity = new PostEntity();
        postEntity.setId(entity.getPostEntity().getId());
        entityToSave.setPostId(postEntity);
        AnswerEntity savedEntity = answerRepository.save(entityToSave);
        response.setErrorMessage(Messages.GENERAL_SUCCEED_MESSAGE);
        response.setStatusCode(HttpStatus.OK);
        response.setAnswerEntity(savedEntity);
        return response;
    }
}
