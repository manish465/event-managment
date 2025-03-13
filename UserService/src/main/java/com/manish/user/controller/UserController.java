package com.manish.user.controller;

import com.manish.user.dto.FetchUserResponseDTO;
import com.manish.user.dto.GeneralSuccessResponseDTO;
import com.manish.user.dto.UserSignUpRequestDTO;
import com.manish.user.dto.UserUpdateRequestDTO;
import com.manish.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j
public class UserController {
    private final UserService userService;

    @PostMapping("/user/create")
    public ResponseEntity<GeneralSuccessResponseDTO> addUsers(@RequestBody UserSignUpRequestDTO userSignUpRequestDTO) {
         return new ResponseEntity<>(userService.addUsers(userSignUpRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/user/user")
    public ResponseEntity<FetchUserResponseDTO> getUsers(@RequestParam List<String> emails) {
        return new ResponseEntity<>(userService.getUsers(emails), HttpStatus.OK);
    }

    @PutMapping("/user/user")
    public ResponseEntity<GeneralSuccessResponseDTO> updateUsers(
            @RequestBody UserUpdateRequestDTO userUpdateRequestDTO
    ) {
        return new ResponseEntity<>(userService.updateUsers(userUpdateRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/user/user")
    public ResponseEntity<GeneralSuccessResponseDTO> deleteUsers(@RequestParam List<String> emails) {
        return new ResponseEntity<>(userService.deleteUsers(emails), HttpStatus.OK);
    }

    @DeleteMapping("/user/add")
    public ResponseEntity<GeneralSuccessResponseDTO> deleteAllUsers() {
        return new ResponseEntity<>(userService.deleteAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/health-check")
    public ResponseEntity<GeneralSuccessResponseDTO> healthCheck(){
        return new ResponseEntity<>(new GeneralSuccessResponseDTO("OK"), HttpStatus.OK);
    }
}