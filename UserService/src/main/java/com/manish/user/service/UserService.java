package com.manish.user.service;

import com.manish.user.dto.*;
import com.manish.user.exception.ApplicationException;
import com.manish.user.model.RoleModel;
import com.manish.user.model.UserModel;
import com.manish.user.repository.RoleRepository;
import com.manish.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public GeneralSuccessResponseDTO addUsers(UserSignUpRequestDTO userSignUpRequestDTO) {
        List<String> emails = userSignUpRequestDTO.getItems()
                .stream()
                .map(UserSignUpDTO::getEmail)
                .toList();

        List<UserModel> existingUsers = userRepository.findAllByEmailIn(emails);
        if(existingUsers != null && !existingUsers.isEmpty()) throw new ApplicationException("User already exists");

        List<UserModel> usersToSave = userSignUpRequestDTO.getItems().stream()
                .map(userDTO -> UserModel.builder()
                        .name(userDTO.getName())
                        .email(userDTO.getEmail())
                        .passwordHash(passwordEncoder.encode(userDTO.getPassword()))
                        .roles(mapRoles(userDTO.getRoles()))
                        .build()
                ).toList();

        userRepository.saveAll(usersToSave);
        return new GeneralSuccessResponseDTO("User added successfully");
    }

    public FetchUserResponseDTO getUsers(List<String> emails) {
        FetchUserResponseDTO fetchUserResponseDTO = new FetchUserResponseDTO();
        fetchUserResponseDTO
                .setItems(
                        userRepository
                                .findAllByEmailIn(emails)
                                .stream()
                                .map(userDTO -> FetchUserDTO.builder()
                                        .id(userDTO.getId())
                                        .name(userDTO.getName())
                                        .email(userDTO.getEmail())
                                        .bio(userDTO.getBio())
                                        .createdAt(userDTO.getCreatedAt())
                                        .updatedAt(userDTO.getUpdatedAt())
                                        .build())
                                .toList()
                );

        return fetchUserResponseDTO;
    }

    public GeneralSuccessResponseDTO updateUsers(UserUpdateRequestDTO userUpdateRequestDTO) {
        List<String> userIds = userUpdateRequestDTO.getItems()
                .stream()
                .map(UserSignUpDTO::getId)
                .toList();

        // Fetch existing users by ID
        List<UserModel> existingUsers = userRepository.findAllById(userIds);
        if (existingUsers.isEmpty()) {
            throw new ApplicationException("Users not found");
        }

        // Map user updates
        List<UserModel> updatedUsers = existingUsers.stream().map(user -> {
            userUpdateRequestDTO.getItems().stream()
                    .filter(updateDTO -> updateDTO.getId().equals(user.getId()))
                    .findFirst()
                    .ifPresent(updateDTO -> {
                        if (updateDTO.getName() != null) user.setName(updateDTO.getName());
                        if (updateDTO.getEmail() != null) user.setEmail(updateDTO.getEmail());
                        if (updateDTO.getPassword() != null) {
                            user.setPasswordHash(passwordEncoder.encode(updateDTO.getPassword()));
                        }
                        if (updateDTO.getRoles() != null && !updateDTO.getRoles().isEmpty()) {
                            user.setRoles(mapRoles(updateDTO.getRoles()));
                        }
                    });

            return user;
        }).toList();

        userRepository.saveAll(updatedUsers);
        return new GeneralSuccessResponseDTO("Users updated successfully");
    }

    public GeneralSuccessResponseDTO deleteUsers(List<String> emails) {
        List<UserModel> existingUsers = userRepository.findAllByEmailIn(emails);
        if (existingUsers.isEmpty()) {
            throw new ApplicationException("Users not found");
        }

        userRepository.deleteAll(existingUsers);
        return new GeneralSuccessResponseDTO("Users deleted successfully");
    }

    private Set<RoleModel> mapRoles(List<String> roleNames) {
        return new HashSet<>(roleRepository.findByNameIn(roleNames));
    }

    public GeneralSuccessResponseDTO deleteAllUsers() {
        userRepository.deleteAll();
        return new GeneralSuccessResponseDTO("All users deleted successfully");
    }
}
