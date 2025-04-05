package com.manish.user.repository;

import com.manish.user.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query("SELECT COUNT(r) > 0 FROM RoleEntity r JOIN r.path p WHERE p = :path AND r.id IN :roleIds")
    boolean existsRoleWithPathAndIdIn(String path, List<Long> roleIds);
}
