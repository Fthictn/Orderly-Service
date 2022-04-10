package com.orderly.repository;

import com.orderly.entity.ProjectEntity;
import org.assertj.core.api.Assertions;
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

    @DisplayName("Unit test for Project save operation")
    @Test
    public void givenProjectObject_whenSaved_thenReturnedProject(){
        ProjectEntity projectEntity = ProjectEntity.builder()
                .projectCode("PRJ")
                .projectName("Orderly")
                .build();

        ProjectEntity savedProject = projectRepository.save(projectEntity);

        Assertions.assertThat(savedProject).isNotNull();
        Assertions.assertThat(savedProject.getId()).isGreaterThan(0);
    }

    @DisplayName("Unit test for Project find all operation")
    @Test
    public void givenProjectsList_whenFindAll_thenProjectsList(){
        //given
        ProjectEntity projectEntity = ProjectEntity.builder()
                .projectCode("PRJ")
                .projectName("Orderly")
                .build();

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

    @Test
    void findByProjectName() {
    }

    @Test
    void findByUserId() {
    }

    @Test
    void findByProjectCode() {
    }
}