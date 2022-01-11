package com.orderly.repository;

import com.orderly.entity.ProjectEntity;
import com.orderly.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

class ProjectRepositoryTest {

    @Mock
    ProjectRepository projectRepository;

    @Test
    void findByProjectName() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(1);
        projectEntity.setProjectName("ProjectName");

        projectRepository.save(projectEntity);

        assertEquals(projectRepository.getOne(1).getProjectName(),projectEntity.getProjectName());
    }

    @Test
    void findByUserId() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(3);

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(1);
        projectEntity.setUserId(userEntity);

        projectRepository.save(projectEntity);

        assertEquals(projectEntity.getUserId().getId(),projectRepository.getOne(1).getUserId().getId());
    }

    @Test
    void findByProjectCode() {
    }
}