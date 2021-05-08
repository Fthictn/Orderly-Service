package com.orderly.repository;

import com.orderly.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    List<UserEntity> findByUserEmailAndUserPassword(String email,String password);
    UserEntity findByUserEmail(String email);
    UserEntity findById(int id);
}
