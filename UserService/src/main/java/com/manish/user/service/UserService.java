package com.manish.user.service;

import com.manish.common.response.GeneralMessageResponseDTO;
import com.manish.common.response.GetUserResponseDTO;
import com.manish.common.request.UpdateUserRequestDTO;
import com.manish.common.request.UserSignUpRequestDTO;
import com.manish.user.entity.UserEntity;
import com.manish.user.exception.ApplicationException;
import com.manish.user.mapper.UserMapper;
import com.manish.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public GeneralMessageResponseDTO addUser(UserSignUpRequestDTO userSignUpRequestDTO) {
        log.info("Add User request received for user-data {}", userSignUpRequestDTO);

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userSignUpRequestDTO.getEmail());
        if(userEntityOptional.isPresent()) throw new ApplicationException("User already exists");

        UserEntity userEntity = UserMapper.toEntity(
                userSignUpRequestDTO,
                passwordEncoder.encode(userSignUpRequestDTO.getPassword()),
                "https://modii.org/wp-content/uploads/2020/12/random.png"
        );

        userRepository.save(userEntity);
        return new GeneralMessageResponseDTO("User sign up request received");
    }

    public List<GetUserResponseDTO> getUser(List<String> userIds) {
        log.info("Get User request received for user-id {}", userIds);
        List<UserEntity> userEntityList = userRepository.findAllById(userIds);
        return userEntityList.stream().map(UserMapper::toDTO).toList();
    }

    public GeneralMessageResponseDTO updateUser(UpdateUserRequestDTO updateUserRequestDTO) {
        log.info("Update User request received for user-data {}", updateUserRequestDTO);

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(updateUserRequestDTO.getEmail());
        if(userEntityOptional.isEmpty()) throw new ApplicationException("User not found");

        UserEntity userEntity = UserMapper.toEntity(
                userEntityOptional.get(),
                updateUserRequestDTO,
                "https://modii.org/wp-content/uploads/2020/12/random.png"
        );
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
