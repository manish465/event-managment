package com.manish.user.controller;

import com.manish.user.dto.GeneralSuccessResponseDTO;
import com.manish.user.dto.UserSignUpRequestDTO;
import com.manish.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j
public class UserController {
    private UserService userService;

    @PostMapping("/user/create")
    public ResponseEntity<GeneralSuccessResponseDTO> addUsers(@RequestBody UserSignUpRequestDTO userSignUpRequestDTO) {
        // return new ResponseEntity<>(userService.addUsers(userSignUpRequestDTO), HttpStatus.CREATED);
        return null;
    }

    @GetMapping("/user/health-check")
    public ResponseEntity<GeneralSuccessResponseDTO> healthCheck(){
        return new ResponseEntity<>(new GeneralSuccessResponseDTO("OK"), HttpStatus.OK);
    }
}