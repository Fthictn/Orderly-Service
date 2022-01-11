package com.orderly.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

class AnswerEntityTest {

    @Autowired
    AnswerEntity entity;

    @Test
    void testToString() {

    }

    @Test
    void setId() {
        int id = 1;
        entity = new AnswerEntity();
        entity.setId(id);
        assertEquals(1,entity.getId());
    }

    @Test
    void setPostId() {

    }

    @Test
    void setContent() {
    }

    @Test
    void setCreatedTime() {
    }

    @Test
    void setIsCorrect() {
    }

    @Test
    void getId() {
    }

    @Test
    void getPostId() {
    }

    @Test
    void getContent() {
    }

    @Test
    void getCreatedTime() {
    }

    @Test
    void getIsCorrect() {
    }
}