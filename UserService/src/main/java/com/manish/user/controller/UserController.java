package com.manish.user.controller;

import com.manish.common.request.UpdateUserRequestDTO;
import com.manish.common.request.UserAccessRequestDTO;
import com.manish.common.request.UserSignUpRequestDTO;
import com.manish.common.response.GeneralMessageResponseDTO;
import com.manish.common.response.GetRoleResponseDTO;
import com.manish.common.response.GetUserResponseDTO;
import com.manish.user.service.AccessService;
import com.manish.user.service.RoleService;
import com.manish.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final AccessService accessService;

    @PostMapping("/api/v1/user/add-user")
    public ResponseEntity<GeneralMessageResponseDTO> addUser(@ModelAttribute @Valid UserSignUpRequestDTO userSignUpRequestDTO) {
        log.info("Add User request received for user-data {}", userSignUpRequestDTO);
        return new ResponseEntity<>(userService.addUser(userSignUpRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/user/get-user")
    public ResponseEntity<List<GetUserResponseDTO>> getUser(@RequestParam("user-id") List<String> userIds) {
        log.info("Get User request received for user-id {}", userIds);
        return new ResponseEntity<>(userService.getUser(userIds), HttpStatus.OK);
    }

    @PutMapping("/api/v1/user/update-user")
    public ResponseEntity<GeneralMessageResponseDTO> updateUser(@ModelAttribute @Valid UpdateUserRequestDTO updateUserRequestDTO) {
        log.info("Update User request received for user-data {}", updateUserRequestDTO);
        return new ResponseEntity<>(userService.updateUser(updateUserRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/user/delete-user")
    public ResponseEntity<GeneralMessageResponseDTO> deleteUser(@RequestParam("user-id") List<String> userIds) {
        log.info("Delete User request received for userIds {}", userIds);
        return new ResponseEntity<>(userService.deleteUser(userIds), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/user/delete-all-user")
    public ResponseEntity<GeneralMessageResponseDTO> deleteAllUser() {
        log.info("Delete All User request received");
        return new ResponseEntity<>(userService.deleteAllUser(), HttpStatus.OK);
    }

    @GetMapping("/api/v1/user/add-role")
    public ResponseEntity<GeneralMessageResponseDTO> addRole(@RequestParam("role") String role) {
        log.info("Add Role request received for role {}", role);
        return new ResponseEntity<>(roleService.addRole(role), HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/user/get-role")
    public ResponseEntity<List<GetRoleResponseDTO>> getRole(@RequestParam("role") List<String> roles) {
        log.info("Get Role request received for roles {}", roles);
        return new ResponseEntity<>(roleService.getRole(roles), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/user/delete-role")
    public ResponseEntity<GeneralMessageResponseDTO> deleteRole(@RequestParam("role") String role) {
        log.info("Delete Role request received for role {}", role);
        return new ResponseEntity<>(roleService.deleteRole(role), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/user/delete-all-role")
    public ResponseEntity<GeneralMessageResponseDTO> deleteAllRole() {
        log.info("Delete All Role request received");
        return new ResponseEntity<>(roleService.deleteAllRole(), HttpStatus.OK);
    }

    @PostMapping("/api/v1/user/add-access")
    public ResponseEntity<GeneralMessageResponseDTO> addAccess(@RequestBody @Valid UserAccessRequestDTO userAccessRequestDTO) {
        log.info("Add Access request received for access-data {}", userAccessRequestDTO);
        return new ResponseEntity<>(accessService.addAccess(userAccessRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/v1/user/delete-access")
    public ResponseEntity<GeneralMessageResponseDTO> deleteAccess(@RequestBody @Valid UserAccessRequestDTO userAccessRequestDTO) {
        log.info("Delete Access request received for access-data {}", userAccessRequestDTO);
        return new ResponseEntity<>(accessService.deleteAccess(userAccessRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/api/v1/user/health-check")
    public ResponseEntity<GeneralMessageResponseDTO> healthCheck() {
        log.info("User health check request received");
        return new ResponseEntity<>(new GeneralMessageResponseDTO("User service is up and running"), HttpStatus.OK);
    }
}
