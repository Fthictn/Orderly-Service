package com.orderly.repository;

import com.orderly.entity.AnswerEntity;
import com.orderly.entity.PostEntity;
import com.orderly.entity.ProjectEntity;
import com.orderly.entity.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @DisplayName("Unit test for User save operation")
    @Test
    public void givenUserObjet_whenSave_thenReturnedUser(){
        UserEntity userEntity = UserEntity.builder()
                .userEmail("fethicetin@gmail.com")
                .userPassword("123")
                .userNameSurname("Fethi Çetin")
                .userRole("Software Engineer")
                .userTitle("Engineer")
                .isBanned("0")
                .build();

        UserEntity savedUser = userRepository.save(userEntity);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @DisplayName("Unit test for User find all operation")
    @Test
    public void givenUserList_whenFindAllUsers_thenRetunedUserList(){
        //given
        UserEntity userEntity = UserEntity.builder()
                .userEmail("fethicetin@gmail.com")
                .userPassword("123")
                .userNameSurname("Fethi Çetin")
                .userRole("Software Engineer")
                .userTitle("Engineer")
                .isBanned("0")
                .build();

        UserEntity userEntity1 = UserEntity.builder()
                .userEmail("fethicetin@gmail.com")
                .userPassword("123")
                .userNameSurname("Fethi Çetin")
                .userRole("Software Engineer")
                .userTitle("Engineer")
                .isBanned("0")
                .build();

        userRepository.save(userEntity);
        userRepository.save(userEntity1);

        //when
        List<UserEntity> userEntityList = userRepository.findAll();

        //then
        Assertions.assertThat(userEntityList).isNotNull();
        Assertions.assertThat(userEntityList.size()).isEqualTo(2);
    }

    @Test
    void findByUserEmailAndUserPassword() {
    }

    @Test
    void findByUserEmail() {
    }

    @DisplayName("Unit test for find user by id")
    @Test
    public void givenUserObject_whenFindUserById_thenReturnedUserObject(){
        //given
        UserEntity userEntity = UserEntity.builder()
                .userEmail("fethicetin@gmail.com")
                .userPassword("123")
                .userNameSurname("Fethi Çetin")
                .userRole("Software Engineer")
                .userTitle("Engineer")
                .isBanned("0")
                .build();

        UserEntity savedEntity = userRepository.save(userEntity);

        //when
        UserEntity entity = userRepository.findById(savedEntity.getId());

        //then
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity).isEqualTo(savedEntity);

    }

    @Test
    void findUserEntityByUserNameSurnameAndUserPassword() {
    }

    @Test
    void findUserEntityByUserNameSurname() {

    }
}