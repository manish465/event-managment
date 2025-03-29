package com.manish.user.repository;

import com.manish.user.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
