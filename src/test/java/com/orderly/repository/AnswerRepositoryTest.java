package com.orderly.repository;

import com.orderly.entity.AnswerEntity;
import com.orderly.entity.PostEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

@DataJpaTest
class AnswerRepositoryTest {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    PostRepository postRepository;

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

    @DisplayName("Unit test for get answer by id")
    @Test
    public void givenAnswerObject_whenFindAnswerById_thenReturnAnswerObject(){
        AnswerEntity answerEntity = AnswerEntity.builder()
                .content("Bu bir cevaptır")
                .createdTime("2021-03-14T17:12:15.438")
                .isCorrect("1")
                .build();

        AnswerEntity savedEntity = answerRepository.save(answerEntity);

        AnswerEntity entity = answerRepository.findById(savedEntity.getId()).get();

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity).isEqualTo(savedEntity);
    }

    @DisplayName("Unit test for find Answer by post id operation")
    @Test
    public void givenAnswerObject_whenFindAnswerByPostId_thenReturnedAnswerList(){
        //given
        PostEntity postEntity = PostEntity.builder()
                .postContent("Post içeriğidir")
                .postTitle("Post başlığıdır")
                .createdTime("2021-03-16T01:18:12.214")
                .isSolved("1")
                .type("soru")
                .build();

        PostEntity savedPostEntity = postRepository.save(postEntity);

        AnswerEntity answerEntity = AnswerEntity.builder()
                .content("Bu bir cevaptır")
                .createdTime("2021-03-14T17:12:15.438")
                .isCorrect("1")
                .postId(savedPostEntity)
                .build();

        AnswerEntity savedEntity = answerRepository.save(answerEntity);

        //when
        List<AnswerEntity> entity = answerRepository.findByPostId(postEntity);

        //then
        Assertions.assertThat(entity.get(0)).isEqualTo(savedEntity);
    }
}