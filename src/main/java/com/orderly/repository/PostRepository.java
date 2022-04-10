package com.orderly.repository;

import com.orderly.entity.PostEntity;
import com.orderly.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Integer> {
    List<PostEntity> findByProjectId(final ProjectEntity id);
}