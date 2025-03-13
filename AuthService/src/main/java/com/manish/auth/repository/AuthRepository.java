package com.manish.auth.repository;

import com.manish.auth.model.AuthModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends MongoRepository<AuthModel, String> {
    Optional<AuthModel> findByEmail(String email);
}
