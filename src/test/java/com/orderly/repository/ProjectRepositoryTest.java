package com.orderly.repository;

import com.orderly.entity.ProjectEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjectRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;

    ProjectEntity projectEntity;

    @BeforeEach
    public void setUp(){
        projectEntity = ProjectEntity.builder()
                .projectCode("PRJ")
                .projectName("Orderly")
                .build();
    }

    @DisplayName("Unit test for Project save operation")
    @Test
    public void givenProjectObject_whenSaved_thenReturnedProject(){
        ProjectEntity savedProject = projectRepository.save(projectEntity);
        Assertions.assertThat(savedProject).isNotNull();
        Assertions.assertThat(savedProject.getId()).isGreaterThan(0);
    }

    @DisplayName("Unit test for Project find all operation")
    @Test
    public void givenProjectsList_whenFindAll_thenProjectsList(){
        //given
        ProjectEntity projectEntity1 = ProjectEntity.builder()
                .projectCode("PRJ1")
                .projectName("Orderly")
                .build();

        projectRepository.save(projectEntity);
        projectRepository.save(projectEntity1);

        //when
        List<ProjectEntity> projectEntityList = projectRepository.findAll();

        //then
        Assertions.assertThat(projectEntityList).isNotNull();
        Assertions.assertThat(projectEntityList.size()).isEqualTo(2);
    }

    @DisplayName("Unit test for find project by id")
    @Test
    public void givenProjectObject_whenFindProjectById_thenReturnedProjectObject(){
        //given
        ProjectEntity savedEntity = projectRepository.save(projectEntity);

        //when
        ProjectEntity entity = projectRepository.findById(savedEntity.getId()).get();

        //then
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity).isEqualTo(savedEntity);

    }

    @DisplayName("Unit test for find Project by project name")
    @Test
    public void given_when_then(){
        //given
        projectRepository.save(projectEntity);
        //when
        ProjectEntity entity = projectRepository.findByProjectName(projectEntity.getProjectName());
        //then
        Assertions.assertThat(entity.getProjectName()).isEqualTo(projectEntity.getProjectName());
        Assertions.assertThat(entity).isNotNull();

    }

    @Test
    void findByUserId() {
    }

    @Test
    void findByProjectCode() {
    }
}