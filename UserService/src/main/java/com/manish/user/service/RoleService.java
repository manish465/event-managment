package com.manish.user.service;

import com.manish.common.response.GeneralMessageResponseDTO;
import com.manish.common.response.GetRoleResponseDTO;
import com.manish.user.entity.RoleEntity;
import com.manish.user.exception.ApplicationException;
import com.manish.user.mapper.RoleMapper;
import com.manish.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public GeneralMessageResponseDTO addRole(String role) {
        log.info("Add Role request received for role {}", role);

        Optional<RoleEntity> optionalRoleEntity = roleRepository.findByRole(role);
        if(optionalRoleEntity.isPresent()) throw new ApplicationException("Role already exists");

        RoleEntity roleEntity = RoleMapper.toEntity(role);

        roleRepository.save(roleEntity);

        return new GeneralMessageResponseDTO("Role added successfully");
    }

    public List<GetRoleResponseDTO> getRole(List<String> roles) {
        log.info("Get Role request received for roles {}", roles);

        List<RoleEntity> roleEntityList = roleRepository.findAllByRoleIn(roles);

        return roleEntityList.stream().map(RoleMapper::toDto).toList();
    }

    public GeneralMessageResponseDTO deleteRole(String role) {
        log.info("Delete Role request received for role {}", role);

        Optional<RoleEntity> optionalRoleEntity = roleRepository.findByRole(role);
        if(optionalRoleEntity.isEmpty()) throw new ApplicationException("Role not found");

        roleRepository.deleteByRole(role);

        return new GeneralMessageResponseDTO("Role deleted successfully");
    }

    public GeneralMessageResponseDTO deleteAllRole() {
        log.info("Delete All Role request received");

        roleRepository.deleteAll();

        return new GeneralMessageResponseDTO("All role deleted successfully");
    }
}
