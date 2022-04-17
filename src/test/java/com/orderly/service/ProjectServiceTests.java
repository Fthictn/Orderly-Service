package com.orderly.service;

import com.orderly.entity.ProjectEntity;
import com.orderly.repository.ProjectRepository;
import com.orderly.response.ProjectResponse;
import com.orderly.utils.EntityToDTOConverter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTests {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private EntityToDTOConverter converter;

    @InjectMocks
    private ProjectServiceImpl projectService;

    ProjectEntity projectEntity;

    @BeforeEach
    public void setUp(){
        projectEntity = ProjectEntity.builder()
                .projectCode("PRJ")
                .projectName("Orderly")
                .build();
    }

    @DisplayName("Unit test for get all project method")
    @Test
    public void given_whenFindAllProjects_thenReturnedProjectList(){
        //given
        ProjectEntity projectEntity1 = ProjectEntity.builder()
                .projectCode("PRJ1")
                .projectName("Orderly1")
                .build();

        BDDMockito.given(projectRepository.findAll()).willReturn(Arrays.asList(projectEntity,projectEntity1));

        //when
        ProjectResponse projects = projectService.getAllProjects();

        //then
        Assertions.assertThat(projects.getResponse()).isNotNull();
        Assertions.assertThat(projects.getResponse()).isNotEmpty();
    }

}
