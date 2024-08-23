package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Diagnostic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiagnosticMapper {
    public Diagnostic toEntity(DiagnosticDto dto) {
        Diagnostic diagnostico = new Diagnostic();
        diagnostico.setDateDiagnostic(dto.dateDiagnostic());
        diagnostico.setDescription(dto.description());
        diagnostico.setGravedad(dto.gravedad());
        diagnostico.setNextControlDate(dto.nextControlDate());
        return diagnostico;
    }

    public DiagnosticDto toDto(Diagnostic entity) {
        return new DiagnosticDto(
                entity.getDateDiagnostic(),
                entity.getDescription(),
                entity.getGravedad(),
                entity.getNextControlDate()
        );
    }

    public List<DiagnosticDto> toDtoList(List<Diagnostic> diagnosticList) {
        return diagnosticList.stream().map(this::toDto).toList();
    }
}
