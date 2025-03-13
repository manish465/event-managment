package com.manish.auth.service;

import com.manish.auth.dto.*;
import com.manish.auth.exception.ApplicationException;
import com.manish.auth.model.AuthModel;
import com.manish.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final JwtService jwtService;
    private final KafkaTemplate<String, UserSignUpRequestDTO> userSignUpKafkaTemplate;

    public GeneralSuccessResponseDTO userSignUp(UserSignUpRequestDTO userSignUpRequestDTO) {
        for(UserSignUpDTO userSignUpDTO : userSignUpRequestDTO.getItems()) {
            Optional<AuthModel> authModel = authRepository.findByEmail(userSignUpDTO.getEmail());

            if(authModel.isPresent()) throw new ApplicationException("User already exists");
            authRepository.save(AuthModel.builder().email(userSignUpDTO.getEmail()).build());
        }

        userSignUpKafkaTemplate.send("user-signup-topic", userSignUpRequestDTO);

        return new GeneralSuccessResponseDTO("User registered successfully");
    }

    public UserLogInResponseDTO userLogIn(UserLogInRequestDTO userLogInRequestDTO) {
        Optional<AuthModel> authModel = authRepository.findByEmail(userLogInRequestDTO.getEmail());

        if(authModel.isEmpty()) throw  new ApplicationException("User not found");

        String accessToken = jwtService.generateAccessToken(userLogInRequestDTO.getEmail());
        String refreshToken = jwtService.generateRefreshToken(userLogInRequestDTO.getEmail());

        authModel.get().setRefreshToken(refreshToken);
        authRepository.save(authModel.get());

        return UserLogInResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public GeneralSuccessResponseDTO userAccessTokenValidate(String authorizationHeader) {
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) throw new ApplicationException("Invalid token");
        String token = authorizationHeader.substring(7);
        if(!jwtService.isTokenValid(token)) throw new ApplicationException("Invalid token");

        String email = jwtService.extractEmail(token);
        Optional<AuthModel> authModel = authRepository.findByEmail(email);

        if(authModel.isEmpty()) throw new ApplicationException("Invalid token");
        if(!authModel.get().getRefreshToken().equals(token)) throw new ApplicationException("Invalid token");

        return new GeneralSuccessResponseDTO("Login Success");
    }

    public UserLogInResponseDTO userAccessTokenRefresh(UserTokenRefreshRequestDTO userTokenRefreshRequestDTO) {
        String email = jwtService.extractEmail(userTokenRefreshRequestDTO.getRefreshToken());
        Optional<AuthModel> authModel = authRepository.findByEmail(email);

        if(authModel.isEmpty()) throw new ApplicationException("Invalid token");
        if(!authModel.get().getRefreshToken().equals(userTokenRefreshRequestDTO.getRefreshToken()))
            throw new ApplicationException("Invalid token");

        String accessToken = jwtService.generateAccessToken(email);

        return UserLogInResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(userTokenRefreshRequestDTO.getRefreshToken())
                .build();
    }

    public GeneralSuccessResponseDTO userLogout(List<String> emails) {
        for(String email : emails) {
            Optional<AuthModel> authModel = authRepository.findByEmail(email);

            if(authModel.isEmpty()) throw new ApplicationException("User not found");

            authModel.get().setRefreshToken(null);
            authRepository.save(authModel.get());
        }

        return new GeneralSuccessResponseDTO("Logout Success");
    }

    public GeneralSuccessResponseDTO deleteUsers(List<String> emails) {
        for(String email : emails) {
            Optional<AuthModel> authModel = authRepository.findByEmail(email);
            if(authModel.isEmpty()) throw new ApplicationException("User not found");
            authRepository.delete(authModel.get());
        }

        return new GeneralSuccessResponseDTO("Users deleted successfully");
    }

    public GeneralSuccessResponseDTO deleteAll() {
        authRepository.deleteAll();
        return new GeneralSuccessResponseDTO("All users deleted successfully");
    }
}
