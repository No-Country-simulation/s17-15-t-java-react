package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumGravedad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DiagnosticDto (
        @NotNull(message = "La fecha de diagnostico es obligatoria")
        LocalDate diagnosisDate,
        @NotBlank(message = "La descripción es obligatoria")
        String description,
        @NotNull(message = "La gravedad es obligatoria")
        EnumGravedad severidad,
        @NotNull(message = "La fecha de control es obligatoria")
        LocalDate nextCheckUp
    //Consulta consulta
    //Tratamiento tratamiento
){
}
