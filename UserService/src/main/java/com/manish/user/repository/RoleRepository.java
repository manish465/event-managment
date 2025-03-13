package com.manish.user.repository;

import com.manish.user.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, String> {
    Set<RoleModel> findByNameIn(List<String> names);
}
