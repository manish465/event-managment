package com.manish.user.repository;

import com.manish.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    List<UserModel> findAllByEmailIn(List<String> emails);
}
