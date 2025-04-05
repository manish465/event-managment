package com.manish.user.controller;

import com.manish.user.dto.*;
import com.manish.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/api/v1/user/add-user")
    public ResponseEntity<GeneralMessageResponseDTO> addUser(@RequestPart("user-data") UserSignUpRequestDTO userSignUpRequestDTO,
                                                             @RequestPart("file-data") MultipartFile profilePicture) {
        log.info("Add User request received for user-data {} and file-data {}", userSignUpRequestDTO, profilePicture);
        return new ResponseEntity<>(userService.addUser(userSignUpRequestDTO, profilePicture), HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/user/get-user")
    public ResponseEntity<List<GetUserResponseDTO>> getUser(@RequestParam("user-id") List<String> userIds) {
        log.info("Get User request received for user-id {}", userIds);
        return null;
    }

    @PutMapping("/api/v1/user/update-user")
    public ResponseEntity<GeneralMessageResponseDTO> updateUser(@RequestPart("user-data") UpdateUserRequestDTO updateUserRequestDTO,
                                                                @RequestPart("file-data") MultipartFile profilePicture) {
        log.info("Update User request received for user-data {} and file-data {}", updateUserRequestDTO, profilePicture);
        return null;
    }

    @DeleteMapping("/api/v1/user/delete-user")
    public ResponseEntity<GeneralMessageResponseDTO> deleteUser(@RequestParam("user-id") List<String> userIds) {
        log.info("Delete User request received for userIds {}", userIds);
        return null;
    }

    @DeleteMapping("/api/v1/user/delete-all-user")
    public ResponseEntity<GeneralMessageResponseDTO> deleteAllUser() {
        log.info("Delete All User request received");
        return null;
    }

    @GetMapping("/api/v1/user/add-role")
    public ResponseEntity<GeneralMessageResponseDTO> addRole(@RequestParam("role") List<String> roles) {
        log.info("Add Role request received for roles {}", roles);
        return null;
    }

    @GetMapping("/api/v1/user/get-role")
    public ResponseEntity<List<GetRoleResponseDTO>> getRole(@RequestParam("role") List<String> roles) {
        log.info("Get Role request received for roles {}", roles);
        return null;
    }

    @DeleteMapping("/api/v1/user/delete-role")
    public ResponseEntity<GeneralMessageResponseDTO> deleteRole(@RequestParam("role") String role) {
        log.info("Delete Role request received for role {}", role);
        return null;
    }

    @DeleteMapping("/api/v1/user/delete-all-role")
    public ResponseEntity<GeneralMessageResponseDTO> deleteAllRole() {
        log.info("Delete All Role request received");
        return null;
    }

    @PostMapping("/api/v1/user/add-access")
    public ResponseEntity<GeneralMessageResponseDTO> addAccess(@RequestBody AddUserAccessRequestDTO addUserAccessRequestDTO) {
        log.info("Add Access request received for access-data {}", addUserAccessRequestDTO);
        return null;
    }

    public ResponseEntity<GeneralMessageResponseDTO> deleteAccess(@RequestParam("access") String accessId) {
        log.info("Delete Access request received for access {}", accessId);
        return null;
    }

    @GetMapping("/api/v1/user/health-check")
    public ResponseEntity<GeneralMessageResponseDTO> healthCheck() {
        log.info("User health check request received");
        return new ResponseEntity<>(new GeneralMessageResponseDTO("User service is up and running"), HttpStatus.OK);
    }
}
