package com.orderly.LightDTO.orderly.repository;

import com.orderly.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    List<UserEntity> findByUserEmailAndUserPassword(String email,String password);
    UserEntity findByUserEmail(String email);
    UserEntity findById(int id);
    List<UserEntity> findUserEntityByUserNameSurnameAndUserPassword(String userNameSurname, String userPassword);
    UserEntity findUserEntityByUserNameSurname(String userNameSurname);
}
