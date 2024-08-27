package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiagnosticMapper {
    public DiagnosticEntity toEntity(DiagnosticDto dto) {
        DiagnosticEntity diagnostico = new DiagnosticEntity();
        diagnostico.setDateDiagnostic(dto.dateDiagnostic());
        diagnostico.setDescription(dto.description());
        diagnostico.setSeveridad(dto.gravedad());
        diagnostico.setNextControlDate(dto.nextControlDate());
        return diagnostico;
    }

    public DiagnosticDto toDto(DiagnosticEntity entity) {
        return new DiagnosticDto(
                entity.getDateDiagnostic(),
                entity.getDescription(),
                entity.getSeveridad(),
                entity.getNextControlDate()
        );
    }

    public List<DiagnosticDto> toDtoList(List<DiagnosticEntity> diagnosticList) {
        return diagnosticList.stream().map(this::toDto).toList();
    }
}
