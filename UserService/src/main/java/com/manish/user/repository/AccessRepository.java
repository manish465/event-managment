package com.manish.user.repository;

import com.manish.user.entity.AccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessRepository extends JpaRepository<AccessEntity, String> {
}
