package com.orderly.repository;

import com.orderly.entity.PostEntity;
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
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ProjectRepository projectRepository;

    PostEntity postEntity;

    @BeforeEach
    public void setUp(){
        postEntity = PostEntity.builder()
                .postContent("Post içeriğidir")
                .postTitle("Post başlığıdır")
                .createdTime("2021-03-16T01:18:12.214")
                .isSolved("0")
                .type("soru")
                .build();
    }

    @DisplayName("Unit test for Post save operation")
    @Test
    public void givenPostObject_whenSaved_thenPost(){
        PostEntity savedPost = postRepository.save(postEntity);
        Assertions.assertThat(savedPost).isNotNull();
        Assertions.assertThat(savedPost.getId()).isGreaterThan(0);
    }

    @DisplayName("Unit test for find all Post operation")
    @Test
    public void givenPostsList_whenFindAllPosts_thenPostsList(){
        //given
        PostEntity postEntity1 = PostEntity.builder()
                .postContent("Post içeriğidir")
                .postTitle("Post başlığıdır")
                .createdTime("2021-03-16T01:18:12.214")
                .isSolved("1")
                .type("soru")
                .build();

        PostEntity postEntity2 = PostEntity.builder()
                .postContent("Post içeriğidir")
                .postTitle("Post başlığıdır")
                .createdTime("2021-03-16T01:18:12.214")
                .isSolved("1")
                .type("soru")
                .build();

        postRepository.save(postEntity);
        postRepository.save(postEntity1);

        //when
        List<PostEntity> postEntityList = postRepository.findAll();

        //then
        Assertions.assertThat(postEntityList).isNotNull();
        Assertions.assertThat(postEntityList.size()).isEqualTo(3);

    }

    @DisplayName("Unit test for find post by id operation")
    @Test
    public void givenPostObjet_whenFindPostById_thenReturnedPostObject(){
        //given
        PostEntity savedEntity = postRepository.save(postEntity);
        //when
        PostEntity entity = postRepository.findById(savedEntity.getId()).get();
        //then
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity).isEqualTo(postEntity);
    }

    @DisplayName("Unit test for find post by project id operation")
    @Test
    public void givenProjectObject_whenFindPostByProjectId_thenReturnedPostList(){
        //given
        ProjectEntity projectEntity = ProjectEntity.builder()
                .projectCode("PRJ")
                .projectName("Orderly")
                .build();

        ProjectEntity savedProject = projectRepository.save(projectEntity);

        PostEntity savedPostEntity = postRepository.save(postEntity);

        //when
        List<PostEntity> postEntities = postRepository.findByProjectId(savedProject);

        //then
        Assertions.assertThat(postEntities.get(0)).isEqualTo(savedPostEntity);
    }
}