package com.manish.user.mapper;

import com.manish.common.dto.GetRoleResponseDTO;
import com.manish.user.entity.RoleEntity;

import java.util.ArrayList;

public class RoleMapper {
    public static RoleEntity toEntity(String role) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(role);
        roleEntity.setPath(new ArrayList<>());

        return roleEntity;
    }

    public static GetRoleResponseDTO toDto(RoleEntity roleEntity) {
        GetRoleResponseDTO getRoleResponseDTO = new GetRoleResponseDTO();
        getRoleResponseDTO.setId(roleEntity.getId());
        getRoleResponseDTO.setRole(roleEntity.getRole());
        getRoleResponseDTO.setAllowedPaths(roleEntity.getPath());

        return getRoleResponseDTO;
    }
}
