package com.orderly.repository;

import com.orderly.entity.AnswerEntity;
import com.orderly.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity,Integer> {
    List<AnswerEntity> findByPostId(final PostEntity id);

    @Query("select a from AnswerEntity a where a.isCorrect = ?1")
    AnswerEntity findAnswerEntityByCorrectness(String correctness);

    @Query("select a from AnswerEntity a where a.content =: content")
    AnswerEntity findAnswerEntityBycontent(@Param("content") String content);

    @Query(value = "select a from AnswerEntity a where a.isCorrect = ?1", nativeQuery = true)
    AnswerEntity findAnswerEntityByCont(String content);
}