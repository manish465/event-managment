package com.manish.user.service;

import com.manish.user.dto.GeneralMessageResponseDTO;
import com.manish.user.dto.GetUserResponseDTO;
import com.manish.user.dto.UpdateUserRequestDTO;
import com.manish.user.dto.UserSignUpRequestDTO;
import com.manish.user.entity.RoleEntity;
import com.manish.user.entity.UserEntity;
import com.manish.user.exception.ApplicationException;
import com.manish.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public GeneralMessageResponseDTO addUser(UserSignUpRequestDTO userSignUpRequestDTO, MultipartFile profilePicture) {
        log.info("Add User request received for user-data {} and file-data {}", userSignUpRequestDTO, profilePicture);

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userSignUpRequestDTO.getEmail());
        if(userEntityOptional.isPresent()) throw new ApplicationException("User already exists");

        UserEntity userEntity = UserEntity.builder()
                .firstName(userSignUpRequestDTO.getFirstName())
                .lastName(userSignUpRequestDTO.getLastName())
                .email(userSignUpRequestDTO.getEmail())
                .hashPassword(passwordEncoder.encode(userSignUpRequestDTO.getPassword()))
                .bio(userSignUpRequestDTO.getBio())
                .profilePicture("https://modii.org/wp-content/uploads/2020/12/random.png") // TODO :: call picture service and get picture url string
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(userEntity);
        return new GeneralMessageResponseDTO("User sign up request received");
    }

    public List<GetUserResponseDTO> getUser(List<String> userIds) {
        log.info("Get User request received for user-id {}", userIds);

        List<UserEntity> userEntityList = userRepository.findAllById(userIds);

        return userEntityList.stream()
                .map(
                        userEntity -> GetUserResponseDTO
                                .builder()
                                .id(userEntity.getId())
                                .firstName(userEntity.getFirstName())
                                .lastName(userEntity.getLastName())
                                .email(userEntity.getEmail())
                                .bio(userEntity.getBio())
                                .profilePicture(userEntity.getProfilePicture())
                                .createdAt(userEntity.getCreatedAt())
                                .updatedAt(userEntity.getUpdatedAt())
                                .roles(userEntity.getRoles().stream().map(RoleEntity::getRole).toList())
                                .build()
                ).toList();
    }

    public GeneralMessageResponseDTO updateUser(UpdateUserRequestDTO updateUserRequestDTO, MultipartFile profilePicture) {
        log.info("Update User request received for user-data {} and file-data {}", updateUserRequestDTO, profilePicture);

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(updateUserRequestDTO.getEmail());
        if(userEntityOptional.isEmpty()) throw new ApplicationException("User not found");

        UserEntity userEntity = userEntityOptional.get();
        userEntity.setFirstName(updateUserRequestDTO.getFirstName());
        userEntity.setLastName(updateUserRequestDTO.getLastName());
        userEntity.setEmail(updateUserRequestDTO.getEmail());
        userEntity.setBio(updateUserRequestDTO.getBio());
        userEntity.setProfilePicture("https://modii.org/wp-content/uploads/2020/12/random.png"); // TODO :: call picture service and get picture url string
        userEntity.setRoles(updateUserRequestDTO.getRoles().stream().map(role -> RoleEntity.builder().role(role).build()).toList());
        userEntity.setUpdatedAt(LocalDateTime.now());

        userRepository.save(userEntity);

        return new GeneralMessageResponseDTO("User update request received");
    }

    public GeneralMessageResponseDTO deleteUser(List<String> userIds) {
        log.info("Delete User request received for userIds {}", userIds);

        userRepository.deleteAllById(userIds);

        return new GeneralMessageResponseDTO("User delete request received");
    }

    public GeneralMessageResponseDTO deleteAllUser() {
        log.info("Delete All User request received");

        userRepository.deleteAll();

        return new GeneralMessageResponseDTO("All user delete request received");
    }
}
