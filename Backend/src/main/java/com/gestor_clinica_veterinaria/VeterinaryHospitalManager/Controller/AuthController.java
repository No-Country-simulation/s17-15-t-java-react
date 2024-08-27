package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.AuthCreateUserRequestDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.AuthLoginRequestDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.AuthResponseDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {


    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthLoginRequestDto authDto) {
        try {
            AuthResponseDto response = this.userDetailsServiceImpl.loginUser(authDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred during login", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid AuthCreateUserRequestDto authCreateUserDto) {
        log.debug("Received request to register user: {}", authCreateUserDto.username());
        try {
            log.debug("Creating user: {}", authCreateUserDto.username());
            AuthResponseDto response = this.userDetailsServiceImpl.createUser(authCreateUserDto);
            log.debug("User created successfully: {}", authCreateUserDto.username());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error during registration: {}", authCreateUserDto.username(), e);
            return new ResponseEntity<>("An error occurred during registration", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
