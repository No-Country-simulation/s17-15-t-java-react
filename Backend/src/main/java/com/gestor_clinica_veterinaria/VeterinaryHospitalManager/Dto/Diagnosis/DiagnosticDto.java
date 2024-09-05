package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Diagnosis;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumGravedad;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
public record DiagnosticDto (
        String name,
        @NotNull(message = "La fecha de diagnostico es obligatoria")
        LocalDate diagnosisDate,
        String description,
        @NotNull(message = "La gravedad es obligatoria")
        EnumGravedad severidad,
        @NotNull(message = "La fecha de control es obligatoria")
        LocalDate nextCheckUp,
        Long consulta_id
    //Consulta consulta
    //Tratamiento tratamiento
){
}
