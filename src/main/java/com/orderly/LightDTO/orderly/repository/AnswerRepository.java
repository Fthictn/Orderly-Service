package com.orderly.LightDTO.orderly.repository;

import com.orderly.LightDTO.orderly.entity.PostEntity;
import com.orderly.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<com.orderly.LightDTO.orderly.entity.AnswerEntity,Integer> {
    List<com.orderly.LightDTO.orderly.entity.AnswerEntity> findByPostId(final PostEntity id);

    @Query("select a from AnswerEntity a where a.isCorrect = ?1")
    com.orderly.LightDTO.orderly.entity.AnswerEntity findAnswerEntityByCorrectness(String correctness);

    @Query("select a from AnswerEntity a where a.content =: content")
    com.orderly.LightDTO.orderly.entity.AnswerEntity findAnswerEntityBycontent(@Param("content") String content);

    @Query(value = "select a from AnswerEntity a where a.isCorrect = ?1", nativeQuery = true)
    com.orderly.LightDTO.orderly.entity.AnswerEntity findAnswerEntityByCont(String content);
}