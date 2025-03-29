package com.manish.user.service;

import com.manish.user.dto.GeneralMessageResponseDTO;
import com.manish.user.dto.UserSignInRequestDTO;
import com.manish.user.dto.UserSignUpRequestDTO;
import com.manish.user.dto.UserVerifyResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class AuthService {
    public GeneralMessageResponseDTO singUp(UserSignUpRequestDTO userSignUpRequestDTO, MultipartFile profilePicture) {
        log.info("User sign up request received for user-data {} and file-data {}", userSignUpRequestDTO, profilePicture);
        return new GeneralMessageResponseDTO("User sign up request received");
    }

    public GeneralMessageResponseDTO signIn(UserSignInRequestDTO userSignInRequestDTO) {
        log.info("User sign in request received for user-data {}", userSignInRequestDTO);
        return null;
    }

    public Boolean verifyToken(String accessToken) {
        log.info("User verify token request received for access-token {}", accessToken);
        return null;
    }

    public UserVerifyResponseDTO decryptToken(String accessToken) {
        log.info("User decrypt token request received for access-token {}", accessToken);
        return null;
    }
}
