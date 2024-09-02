package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Diagnosis.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import org.springframework.stereotype.Component;

//@Mapper(componentModel = "spring")
@Component
public class DiagnosticMapper {

    /*DiagnosticMapper INSTANCE = Mappers.getMapper(DiagnosticMapper.class);

    DiagnosticDto toDto(DiagnosticEntity entity);

    DiagnosticEntity toEntity(DiagnosticDto dto);*/

    public DiagnosticEntity toEntity(DiagnosticDto dto){

        DiagnosticEntity entity = new DiagnosticEntity();
        entity.setName(dto.name());
        entity.setDiagnosisDate(dto.diagnosisDate());
        entity.setDescription(dto.description());
        entity.setSeveridad(dto.severidad());
        entity.setNextCheckUp(dto.nextCheckUp());
        return entity;
    }
    public DiagnosticDto toDto(DiagnosticEntity entity){

        return new DiagnosticDto(
                entity.getName(),
                entity.getDiagnosisDate(),
                entity.getDescription(),
                entity.getSeveridad(),
                entity.getNextCheckUp()
        );
    }
}