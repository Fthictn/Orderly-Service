package com.orderly.repository;

import com.orderly.entity.ProjectEntity;
import com.orderly.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity,Integer> {
    ProjectEntity findByProjectName(String projectName);
    List<ProjectEntity> findByUserId(final UserEntity id);
    ProjectEntity findByProjectCode(String projectCode);
}
