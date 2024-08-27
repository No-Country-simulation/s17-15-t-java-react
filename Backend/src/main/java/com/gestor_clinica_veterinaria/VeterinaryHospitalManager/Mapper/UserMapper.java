package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.UserDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(UserDto dto) {
        UserEntity user = new UserEntity();
        user.setEmail(dto.email());
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        //user.setRoles(dto.roles());
        return user;
    }


    /*public UserDto toDto(UserEntity entity) {
        return new UserDto(
                entity.getEmail(),
                entity.getUsername(),
                entity.getPassword()
                //entity.getRoles()
        );
    }*/
}
