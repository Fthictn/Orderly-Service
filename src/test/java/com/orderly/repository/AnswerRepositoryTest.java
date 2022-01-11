package com.orderly.repository;

import com.orderly.entity.AnswerEntity;
import com.orderly.entity.PostEntity;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

class AnswerRepositoryTest {

    @Mock
    AnswerRepository answerRepository;
    @Test
    void findByPostId() {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(5);
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setId(3);
        answerEntity.setPostId(postEntity);
        answerRepository.save(answerEntity);
        assertEquals(answerEntity.getPostId(),answerRepository.getOne(3).getPostId());
    }
}