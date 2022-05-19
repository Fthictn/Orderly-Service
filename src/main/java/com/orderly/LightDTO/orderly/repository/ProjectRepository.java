package com.orderly.LightDTO.orderly.repository;

import com.orderly.LightDTO.orderly.entity.ProjectEntity;
import com.orderly.LightDTO.orderly.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<com.orderly.LightDTO.orderly.entity.ProjectEntity,Integer> {
    com.orderly.LightDTO.orderly.entity.ProjectEntity findByProjectName(String projectName);
    List<com.orderly.LightDTO.orderly.entity.ProjectEntity> findByUserId(final UserEntity id);
    ProjectEntity findByProjectCode(String projectCode);
}
