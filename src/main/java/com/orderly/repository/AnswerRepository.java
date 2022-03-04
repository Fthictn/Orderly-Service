package com.orderly.repository;

import com.orderly.entity.AnswerEntity;
import com.orderly.entity.PostEntity;
import com.orderly.entity.ProjectEntity;
import com.orderly.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity,Integer> {
    List<AnswerEntity> findByPostId(final PostEntity id);
}
