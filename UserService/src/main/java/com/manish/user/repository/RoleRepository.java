package com.manish.user.repository;

import com.manish.user.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query("SELECT COUNT(r) > 0 FROM role-table r JOIN r.path p WHERE p = :path AND r.id IN :roleIds")
    boolean existsRoleWithPathAndIdIn(String path, List<Long> roleIds);

    Optional<RoleEntity> findByRole(String role);

    List<RoleEntity> findAllByRoleIn(List<String> roles);

    void deleteByRole(String role);
}
