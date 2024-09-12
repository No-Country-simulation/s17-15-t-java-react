package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Diagnosis;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumGravedad;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DiagnosticResponseDto(
        String name,
        @NotNull(message = "La fecha de diagnostico es obligatoria")
        LocalDate diagnosisDate,
        String description,
        @NotNull(message = "La gravedad es obligatoria")
        EnumGravedad severidad,
        @NotNull(message = "La fecha de control es obligatoria")
        @NotNull(message = "La fecha de control es obligatoria") LocalDate nextCheckUp,
        @NotNull(message = "La consulta es obligatoria") Long consulta_id,
        Long id_diagnostic
) {
}
