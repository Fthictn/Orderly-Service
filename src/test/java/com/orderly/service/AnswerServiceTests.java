package com.orderly.service;


import com.orderly.LightDTO.AnswerLightDTO;
import com.orderly.LightDTO.PostLightDTO;
import com.orderly.entity.AnswerEntity;
import com.orderly.entity.PostEntity;
import com.orderly.repository.AnswerRepository;
import com.orderly.response.AnswerResponse;
import com.orderly.utils.EntityToDTOConverter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import java.time.LocalDateTime;

public class AnswerServiceTests {

    private AnswerRepository answerRepository;
    private AnswerServiceImpl answerService;
    private EntityToDTOConverter converter;
    @BeforeEach
    public void setUp(){
        answerRepository = Mockito.mock(AnswerRepository.class);
        converter = new EntityToDTOConverter();
        answerService = new AnswerServiceImpl(answerRepository, converter);
    }

    @DisplayName("Unit test for create Answer method")
    @Test
    public void givenAnswerObject_whenCreateAnswer_thenReturnedAnswerResponse(){
        //given
        PostLightDTO postLightDTO = new PostLightDTO();
        postLightDTO.setId(1);
        AnswerLightDTO entity = new AnswerLightDTO();
        entity.setContent("answer content");
        entity.setIsCorrect("0");
        entity.setPostEntity(postLightDTO);
        PostEntity postEntity = new PostEntity();
        postEntity.setId(entity.getPostEntity().getId());
        AnswerEntity entityToSave = new AnswerEntity();
        entityToSave.setId(1);
        entityToSave.setContent(entity.getContent());
        entityToSave.setCreatedTime(LocalDateTime.now().toString());
        entityToSave.setIsCorrect(entity.getIsCorrect());
        entityToSave.setPostId(postEntity);
        BDDMockito.given(answerRepository.save(entityToSave)).willReturn(entityToSave);

        //when
        AnswerResponse savedEntity = answerService.createAnswer(entity);

        //then
        Assertions.assertThat(savedEntity).isNotNull();
    }
}
