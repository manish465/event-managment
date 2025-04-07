package com.manish.user.service;

import com.manish.common.dto.GeneralMessageResponseDTO;
import com.manish.common.dto.UserSignInRequestDTO;
import com.manish.common.dto.UserVerifyResponseDTO;
import com.manish.user.entity.UserEntity;
import com.manish.user.exception.ApplicationException;
import com.manish.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public GeneralMessageResponseDTO signIn(UserSignInRequestDTO userSignInRequestDTO) {
        log.info("User sign in request received for user-data {}", userSignInRequestDTO);

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userSignInRequestDTO.getEmail());
        if(userEntityOptional.isEmpty()) throw new ApplicationException("User not found");

        if(!passwordEncoder.matches(userSignInRequestDTO.getPassword(), userEntityOptional.get().getHashPassword()))
            throw new ApplicationException("Invalid password");

        String accessToken = JWTService.generateToken(userEntityOptional.get().getId());
        return new GeneralMessageResponseDTO(accessToken);
    }

    public Boolean verifyToken(String accessToken, String path) {
        log.info("User verify token request received for access-token {}", accessToken);

        if(!JWTService.validateToken(accessToken)) return false;

        String userId = JWTService.getSubjectFromToken(accessToken);
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if(userEntityOptional.isEmpty()) throw new ApplicationException("User not found");
        return roleService.verifyAuthorization(userEntityOptional.get().getRoles(), path);
    }

    public UserVerifyResponseDTO decryptToken(String accessToken) {
        log.info("User decrypt token request received for access-token {}", accessToken);

        String userId = JWTService.getSubjectFromToken(accessToken);
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if(userEntityOptional.isEmpty()) throw new ApplicationException("User not found");

        return new UserVerifyResponseDTO(userId);
    }
}
