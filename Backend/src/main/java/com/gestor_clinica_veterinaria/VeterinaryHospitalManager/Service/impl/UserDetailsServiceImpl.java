package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.impl;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Config.Jwt.JwtUtils;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Auth.AuthCreateUserRequestDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Auth.AuthLoginRequestDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Auth.AuthResponseDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Auth.AuthResponseRegisterDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.RoleEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.UserEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.RoleRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    //private final VeterinarioRepository veterinarioRepository;
    private final JwtUtils jwtUtils;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByusername(username).orElseThrow(() -> new UsernameNotFoundException(
                "El usuario" + username + "no existe"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userEntity.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getEnumRole().name())));
        });

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialsNoExpired(),
                userEntity.isAccountNoLocked(),
                authorities);
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        VeterinarioEntity userEntity = veterinarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                "El veterinario" + username + "no existe"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userEntity.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getEnumRole().name())));
        });

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialsNoExpired(),
                userEntity.isAccountNoLocked(),
                authorities);
    }*/

    public AuthResponseDto loginUser(AuthLoginRequestDto authDto) {
        String username = authDto.username();
        String password = authDto.password();

        Long id = userRepository.findByusername(username)
                .map(UserEntity::getId)
                .orElseThrow(() -> new UsernameNotFoundException("El Id del usuario " + username + " no existe"));

        log.debug("Attempting to authenticate user: {}", username);

        try{
            Authentication authentication = this.authenticate(username, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("User authenticated successfully. Generating JWT token.");
            String token = jwtUtils.generateJwtToken(authentication);
            log.debug("JWT token generated successfully.");
            return new AuthResponseDto(id, username, "User logged successfully", token, true);
        }
        catch (Exception e) {
            log.error("Authentication failed for user: {}", username, e);
            throw e;
        }
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        if(userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

    }

    public AuthResponseRegisterDto createUser(AuthCreateUserRequestDto authCreateUserDto) {

        String username = authCreateUserDto.username();
        String password = authCreateUserDto.password();
        String email = authCreateUserDto.email();

        log.debug("Attempting to create user: username: {}, email: {}, password: {}", username, email, password);
        List<String> roles = authCreateUserDto.roleDto().roles();
        log.debug("User roles: {}", roles);

        Set<RoleEntity> roleEntities = new HashSet<>(roleRepository.findRoleEntitiesByEnumRoleIn(roles));

        log.debug("Role entities: {}", roleEntities);

        if(roleEntities.isEmpty()){
            throw new IllegalArgumentException("Los roles especificados no existen");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .roles(roleEntities)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialsNoExpired(true)
                .build();

        log.debug("User entity: {}", userEntity);

        UserEntity userCreated = userRepository.save(userEntity);
        log.debug("User created: {}", userCreated);
        ArrayList<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();

        log.debug("User roles: {}", userCreated.getRoles());

        userCreated.getRoles().forEach(role -> {
            authoritiesList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getEnumRole().name())));
        });
        log.debug("User authorities: {}", authoritiesList);

        userCreated.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authoritiesList.add(new SimpleGrantedAuthority(permission.getName())));
        log.debug("User permissions: {}", authoritiesList);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getUsername(), userCreated.getPassword(), authoritiesList);
        log.debug("User authentication: {}", authentication);
        String accessToken = jwtUtils.generateJwtToken(authentication);

        log.debug("JWT token generated successfully.");

        return new AuthResponseRegisterDto(username, "User created successfully", accessToken, true);
        //return new AuthResponseRegisterDto(username, "User created successfully", accessToken, true);
    }

    /*public AuthResponseDto createUser(AuthCreateUserRequestDto authCreateUserDto) {

        String username = authCreateUserDto.username();
        String apellido = authCreateUserDto.apellido();
        String password = authCreateUserDto.password();
        String email = authCreateUserDto.email();
        String especialidad = authCreateUserDto.especialidad();
        String tarjetaProfesional = authCreateUserDto.tarjetaProfesional();


        log.debug("Attempting to create user: username: {}, email: {}, password: {}", username, email, password);
        List<String> roles = authCreateUserDto.roleDto().roles();
        log.debug("User roles: {}", roles);

        Set<RoleEntity> roleEntities = new HashSet<>(roleRepository.findRoleEntitiesByEnumRoleIn(roles));

        log.debug("Role entities: {}", roleEntities);

        if(roleEntities.isEmpty()){
            throw new IllegalArgumentException("Los roles especificados no existen");
        }

        VeterinarioEntity veterinarioEntity = new VeterinarioEntity();
        veterinarioEntity.setUsername(username);
        veterinarioEntity.setApellido(apellido);
        veterinarioEntity.setPassword(passwordEncoder.encode(password));
        veterinarioEntity.setEmail(email);
        veterinarioEntity.setEspecialidad(especialidad);
        veterinarioEntity.setTarjetaProfesional(tarjetaProfesional);
        veterinarioEntity.setRoles(roleEntities);
        veterinarioEntity.setEnabled(true);
        veterinarioEntity.setAccountNoLocked(true);
        veterinarioEntity.setAccountNoExpired(true);
        veterinarioEntity.setCredentialsNoExpired(true);


        VeterinarioEntity veterinarioCreated = veterinarioRepository.save(veterinarioEntity);

        ArrayList<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();

        veterinarioCreated.getRoles().forEach(role -> {
            authoritiesList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getEnumRole().name())));
        });

        veterinarioCreated.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authoritiesList.add(new SimpleGrantedAuthority(permission.getName())));

        Authentication authentication = new UsernamePasswordAuthenticationToken(veterinarioCreated.getUsername(), veterinarioCreated.getPassword(), authoritiesList);
        String accessToken = jwtUtils.generateJwtToken(authentication);

        return new AuthResponseDto(username, "Veterinario created successfully", accessToken, true);
    }*/
}
