package com.manish.user.repository;

import com.manish.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    Optional<UserEntity> findByEmail(String email);
}
