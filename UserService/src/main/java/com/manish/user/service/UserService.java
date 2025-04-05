package com.manish.user.service;

import com.manish.user.dto.GeneralMessageResponseDTO;
import com.manish.user.dto.UserSignUpRequestDTO;
import com.manish.user.entity.UserEntity;
import com.manish.user.exception.ApplicationException;
import com.manish.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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
}
