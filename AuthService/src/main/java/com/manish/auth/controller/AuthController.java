package com.manish.auth.controller;

import com.manish.auth.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j
public class AuthController {
    @PostMapping("/auth/register")
    public ResponseEntity<GeneralSuccessResponseDTO> userSignUp(@RequestBody UserSignUpRequestDTO userSignUpRequestDTO) {
        return new ResponseEntity<>(new GeneralSuccessResponseDTO("User registered successfully"), HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserLogInResponseDTO> userLogIn(@RequestBody UserLogInRequestDTO userLogInRequestDTO) {
        return null;
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<UserLogInResponseDTO> userAccessTokenRefresh(@RequestBody UserTokenRefreshRequestDTO userTokenRefreshRequestDTO) {
        return null;
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<GeneralSuccessResponseDTO> userLogout(@RequestParam String email) {
        return null;
    }
}