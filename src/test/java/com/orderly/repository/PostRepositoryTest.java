package com.orderly.repository;

import com.orderly.entity.PostEntity;
import com.orderly.entity.ProjectEntity;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PostRepositoryTest {

    @Mock
    PostRepository postRepo;

    @Before
    public void setUp() {
        postRepo = Mockito.mock(PostRepository.class);
    }

    @Test
    void findByProjectId() {
        PostEntity postEntity = new PostEntity();
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(5);
        postRepo = mock(PostRepository.class);
        postEntity.setProjectId(projectEntity);
        postEntity.setId(3);
        postRepo.save(postEntity);
        List<PostEntity> x = postRepo.findAll();
        assertEquals(postEntity.getProjectId().getId(),postRepo.getOne(3).getProjectId().getId());
    }
}