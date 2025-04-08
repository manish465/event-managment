package com.manish.user.service;

import com.manish.common.response.GeneralMessageResponseDTO;
import com.manish.common.request.UserAccessRequestDTO;
import com.manish.user.entity.RoleEntity;
import com.manish.user.exception.ApplicationException;
import com.manish.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccessService {
    private final RoleRepository roleRepository;

    public GeneralMessageResponseDTO addAccess(UserAccessRequestDTO userAccessRequestDTO) {
        log.info("Add Access request received for access-data {}", userAccessRequestDTO);

        Optional<RoleEntity> optionalRoleEntity = roleRepository.findByRole(userAccessRequestDTO.getRole());
        if(optionalRoleEntity.isEmpty()) throw new ApplicationException("Role not found");
        if(optionalRoleEntity.get().getPath().contains(userAccessRequestDTO.getPath()))
            throw new ApplicationException("Path already exists");

        optionalRoleEntity.get().getPath().add(userAccessRequestDTO.getPath());
        roleRepository.save(optionalRoleEntity.get());

        return new GeneralMessageResponseDTO("Access added successfully");
    }

    public GeneralMessageResponseDTO deleteAccess(UserAccessRequestDTO userAccessRequestDTO) {
        log.info("Delete Access request received for access-data {}", userAccessRequestDTO);

        Optional<RoleEntity> optionalRoleEntity = roleRepository.findByRole(userAccessRequestDTO.getRole());
        if(optionalRoleEntity.isEmpty()) throw new ApplicationException("Role not found");
        if(!optionalRoleEntity.get().getPath().contains(userAccessRequestDTO.getPath()))
            throw new ApplicationException("Path not found");

        optionalRoleEntity.get().getPath().remove(userAccessRequestDTO.getPath());
        roleRepository.save(optionalRoleEntity.get());

        return new GeneralMessageResponseDTO("Access deleted successfully");
    }
}
