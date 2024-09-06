package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Auth.AuthCreateUserRequestDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Auth.AuthLoginRequestDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Auth.AuthResponseDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Auth.AuthResponseRegisterDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.impl.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Authentication", description = "Authentication API")
@Validated
public class AuthController {


    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping("/login")
    @Tag(name = "Authentication", description = "API for user authentication.")
    @Operation(
            summary = "Login",
            description = "Authenticate a user with their credentials.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Credentials required for login",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthLoginRequestDto.class),
                            examples = @ExampleObject(
                                    name = "Login Example",
                                    value = "{ \"username\": \"user123\", \"password\": \"password123\" }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login successful",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponseDto.class),
                                    examples = @ExampleObject(
                                            name = "Success Response",
                                            value = "{ \"username\": \"user123\", \"message\": \"User created successfully\", \"jwt\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\", \"status\": true }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Invalid username or password",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "Unauthorized Response",
                                            value = "\"Invalid username or password\""
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "500", description = "An error occurred during login",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "Internal Server Error",
                                            value = "\"An error occurred during login\""
                                    )
                            )
                    )
            }
    )
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
    @Tag(name = "Authentication", description = "API for user registration.")
    @Operation(
            summary = "Register",
            description = "Register a new user with their credentials.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Credentials required for registration",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthCreateUserRequestDto.class)
                    )
            )
    )
    public ResponseEntity<?> register(@RequestBody @Valid AuthCreateUserRequestDto authCreateUserDto) {
        log.debug("Received request to register user: {}", authCreateUserDto.username());
        try {
            log.debug("Creating user: {}", authCreateUserDto.username());
            AuthResponseRegisterDto response = this.userDetailsServiceImpl.createUser(authCreateUserDto);
            log.debug("User created successfully: {}", authCreateUserDto.username());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error during registration: {}", authCreateUserDto.username(), e);
            return new ResponseEntity<>("An error occurred during registration", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
