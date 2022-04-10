package com.orderly.repository;

import com.orderly.entity.AnswerEntity;
import com.orderly.entity.PostEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AnswerRepositoryTest {

    @Autowired
    private AnswerRepository answerRepository;

    @DisplayName("Unit test for Answer save operation")
    @Test
    public void givenAnswerObject_whenSave_thenReturnedSavedAnswer(){

        //given
        AnswerEntity answerEntity = AnswerEntity.builder()
                .content("Bu bir cevaptır")
                .createdTime("2021-03-14T17:12:15.438")
                .isCorrect("0")
                .build();
        //when
        AnswerEntity savedAnswer = answerRepository.save(answerEntity);

        //then
        Assertions.assertThat(savedAnswer).isNotNull();
        Assertions.assertThat(savedAnswer.getId()).isGreaterThan(0);
    }

    @DisplayName("Unit test for get all Answer operation")
    @Test
    public void givenAnswersList_whenFindAll_thenAnswersList(){
        //given
        AnswerEntity answerEntity = AnswerEntity.builder()
                .content("Bu bir cevaptır")
                .createdTime("2021-03-14T17:12:15.438")
                .isCorrect("1")
                .build();

        AnswerEntity answerEntity1 = AnswerEntity.builder()
                .content("Bu bir sevaptır")
                .createdTime("2021-03-14T17:12:15.438")
                .isCorrect("0")
                .build();

        answerRepository.save(answerEntity);
        answerRepository.save(answerEntity1);

        //when
        List<AnswerEntity> answerEntityList = answerRepository.findAll();

        //then
        Assertions.assertThat(answerEntityList).isNotNull();
        Assertions.assertThat(answerEntityList.size()).isEqualTo(2);

    }

    @Test
    void findByPostId() {
    }
}