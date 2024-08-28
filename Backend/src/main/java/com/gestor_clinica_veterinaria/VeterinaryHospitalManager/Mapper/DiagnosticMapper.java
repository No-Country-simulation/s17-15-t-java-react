package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface DiagnosticMapper {

    DiagnosticEntity toEntity(DiagnosticDto dto);
    DiagnosticDto toDto(DiagnosticEntity entity);
    List<DiagnosticDto> toDtoList(List<DiagnosticEntity> entities);

}
