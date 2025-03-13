package com.manish.auth.controller;

import com.manish.auth.dto.*;
import com.manish.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j
public class AuthController {
    private AuthService authService;

    @PostMapping("/auth/register")
    public ResponseEntity<GeneralSuccessResponseDTO> userSignUp(@RequestBody UserSignUpRequestDTO userSignUpRequestDTO) {
        return new ResponseEntity<>(authService.userSignUp(userSignUpRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserLogInResponseDTO> userLogIn(@RequestBody UserLogInRequestDTO userLogInRequestDTO) {
        return new ResponseEntity<>(authService.userLogIn(userLogInRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/auth/validate")
    public ResponseEntity<GeneralSuccessResponseDTO> userAccessTokenValidate(@RequestHeader("Authorization") String authorizationHeader) {
        return new ResponseEntity<>(authService.userAccessTokenValidate(authorizationHeader), HttpStatus.OK);
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<UserLogInResponseDTO> userAccessTokenRefresh(@RequestBody UserTokenRefreshRequestDTO userTokenRefreshRequestDTO) {
        return new ResponseEntity<>(authService.userAccessTokenRefresh(userTokenRefreshRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<GeneralSuccessResponseDTO> userLogout(@RequestParam List<String> emails) {
        return new ResponseEntity<>(authService.userLogout(emails), HttpStatus.OK);
    }

    @DeleteMapping("/auth/delete")
    public ResponseEntity<GeneralSuccessResponseDTO> deleteUsers(@RequestParam List<String> emails) {
        return new ResponseEntity<>(authService.deleteUsers(emails), HttpStatus.OK);
    }

    @DeleteMapping("/auth/delete-all")
    public ResponseEntity<GeneralSuccessResponseDTO> deleteAll() {
        return new ResponseEntity<>(authService.deleteAll(), HttpStatus.OK);
    }

    @GetMapping("/auth/health-check")
    public ResponseEntity<GeneralSuccessResponseDTO> healthCheck(){
        return new ResponseEntity<>(new GeneralSuccessResponseDTO("OK"), HttpStatus.OK);
    }
}