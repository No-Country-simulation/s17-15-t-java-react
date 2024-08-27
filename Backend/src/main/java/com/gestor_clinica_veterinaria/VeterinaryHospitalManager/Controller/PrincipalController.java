package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.UserDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumRole;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.RoleEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.UserEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.UserMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PrincipalController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;



    @GetMapping("/hello")
    public String hello() {
        return "Hello world not secure";
    }

    @GetMapping("/helloSecure")
    public String helloSecure() {
        return "Hello world secure";
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {

        Set<RoleEntity> roles = userDto.roles()
                .stream().map(role -> RoleEntity.builder()
                        .enumRole(EnumRole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(userDto.username())
                .password(passwordEncoder.encode(userDto.password()))
                .email(userDto.email())
                .roles(roles)
                .build();

        userRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted");
    }
}
