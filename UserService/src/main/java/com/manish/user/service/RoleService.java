package com.manish.user.service;

import com.manish.user.entity.RoleEntity;
import com.manish.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Boolean verifyAuthorization(List<RoleEntity> roles, String path) {
        log.info("User verify authorization request received for roles {} and path {}", roles, path);

        List<Long> roleIds = roles.stream().map(RoleEntity::getId).toList();
        return roleRepository.existsRoleWithPathAndIdIn(path, roleIds);
    }
}
