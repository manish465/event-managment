package com.manish.user.controller;

import com.manish.common.request.UserSignInRequestDTO;
import com.manish.common.request.UserSignUpRequestDTO;
import com.manish.common.response.UserVerifyResponseDTO;
import com.manish.user.service.AuthService;
import com.manish.user.service.UserService;
import com.manish.common.response.GeneralMessageResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/api/v1/auth/sign-up")
    public ResponseEntity<GeneralMessageResponseDTO> singUp(@ModelAttribute @Valid UserSignUpRequestDTO userSignUpRequestDTO) {
        log.info("User sign up request received for user-data {}", userSignUpRequestDTO);
        return new ResponseEntity<>(userService.addUser(userSignUpRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/api/v1/auth/sign-in")
    public ResponseEntity<GeneralMessageResponseDTO> signIn(@RequestBody @Valid UserSignInRequestDTO userSignInRequestDTO) {
        log.info("User sign in request received for user-data {}", userSignInRequestDTO);
        return ResponseEntity.ok(authService.signIn(userSignInRequestDTO));
    }

    @GetMapping("api/v1/auth/verify-token")
    public ResponseEntity<Boolean> verifyToken(@RequestParam("access-token") String accessToken, @RequestParam("path") String path) {
        log.info("User verify token request received for access-token {}", accessToken);
        return ResponseEntity.ok(authService.verifyToken(accessToken, path));
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
