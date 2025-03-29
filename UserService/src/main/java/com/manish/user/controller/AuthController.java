package com.manish.user.controller;

import com.manish.user.dto.*;
import com.manish.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/v1/auth/sign-up")
    public ResponseEntity<GeneralMessageResponseDTO> singUp(@RequestPart("user-data") UserSignUpRequestDTO userSignUpRequestDTO, @RequestPart("file-data") MultipartFile profilePicture) {
        log.info("User sign up request received for user-data {} and file-data {}", userSignUpRequestDTO, profilePicture);
        return new ResponseEntity<>(authService.singUp(userSignUpRequestDTO, profilePicture), HttpStatus.CREATED);
    }

    @PostMapping("/api/v1/auth/sign-in")
    public ResponseEntity<GeneralMessageResponseDTO> signIn(@RequestBody UserSignInRequestDTO userSignInRequestDTO) {
        log.info("User sign in request received for user-data {}", userSignInRequestDTO);
        return ResponseEntity.ok(authService.signIn(userSignInRequestDTO));
    }

    @GetMapping("api/v1/auth/verify-token")
    public ResponseEntity<Boolean> verifyToken(@RequestParam("access-token") String accessToken) {
        log.info("User verify token request received for access-token {}", accessToken);
        return ResponseEntity.ok(authService.verifyToken(accessToken));
    }

    @GetMapping("api/v1/auth/decrypt-token")
    public ResponseEntity<UserVerifyResponseDTO> decryptToken(@RequestParam("access-token") String accessToken) {
        log.info("User decrypt token request received for access-token {}", accessToken);
        return ResponseEntity.ok(authService.decryptToken(accessToken));
    }

    @GetMapping("/api/v1/auth/health-check")
    public ResponseEntity<GeneralMessageResponseDTO> healthCheck() {
        log.info("User health check request received");
        return ResponseEntity.ok(new GeneralMessageResponseDTO("User service is up and running"));
    }
}
