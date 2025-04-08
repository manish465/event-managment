package com.manish.user.mapper;

import com.manish.common.dto.GetUserResponseDTO;
import com.manish.common.dto.UpdateUserRequestDTO;
import com.manish.common.dto.UserSignUpRequestDTO;
import com.manish.user.entity.RoleEntity;
import com.manish.user.entity.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(UserSignUpRequestDTO userSignUpRequestDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userSignUpRequestDTO.getFirstName());
        userEntity.setLastName(userSignUpRequestDTO.getLastName());
        userEntity.setEmail(userSignUpRequestDTO.getEmail());
        userEntity.setHashPassword("");
        userEntity.setBio(userSignUpRequestDTO.getBio());
        userEntity.setProfilePicture("");

        return userEntity;
    }

    public static UserEntity toEntity(UserSignUpRequestDTO userSignUpRequestDTO, String hashPassword, String profilePictureUrl) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userSignUpRequestDTO.getFirstName());
        userEntity.setLastName(userSignUpRequestDTO.getLastName());
        userEntity.setEmail(userSignUpRequestDTO.getEmail());
        userEntity.setHashPassword(hashPassword);
        userEntity.setBio(userSignUpRequestDTO.getBio());
        userEntity.setProfilePicture(profilePictureUrl);

        return userEntity;
    }

    public static UserEntity toEntity(UserEntity userEntity, UpdateUserRequestDTO updateUserRequestDTO) {
        userEntity.setFirstName(updateUserRequestDTO.getFirstName());
        userEntity.setLastName(updateUserRequestDTO.getLastName());
        userEntity.setEmail(updateUserRequestDTO.getEmail());
        userEntity.setBio(updateUserRequestDTO.getBio());
        userEntity.setProfilePicture("");

        return userEntity;
    }

    public static UserEntity toEntity(UserEntity userEntity, UpdateUserRequestDTO updateUserRequestDTO, String profilePictureUrl) {
        userEntity.setFirstName(updateUserRequestDTO.getFirstName());
        userEntity.setLastName(updateUserRequestDTO.getLastName());
        userEntity.setEmail(updateUserRequestDTO.getEmail());
        userEntity.setBio(updateUserRequestDTO.getBio());
        userEntity.setProfilePicture(profilePictureUrl);

        return userEntity;
    }

    public static GetUserResponseDTO toDTO(UserEntity userEntity) {
        GetUserResponseDTO getUserResponseDTO = new GetUserResponseDTO();
        getUserResponseDTO.setId(userEntity.getId());
        getUserResponseDTO.setFirstName(userEntity.getFirstName());
        getUserResponseDTO.setLastName(userEntity.getLastName());
        getUserResponseDTO.setEmail(userEntity.getEmail());
        getUserResponseDTO.setBio(userEntity.getBio());
        getUserResponseDTO.setProfilePicture(userEntity.getProfilePicture());
        getUserResponseDTO.setCreatedAt(userEntity.getCreatedAt());
        getUserResponseDTO.setUpdatedAt(userEntity.getUpdatedAt());
        getUserResponseDTO.setRoles(userEntity.getRoles().stream().map(RoleEntity::getRole).toList());

        return getUserResponseDTO;
    }
}
