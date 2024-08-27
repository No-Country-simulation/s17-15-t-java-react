package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumGravedad;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DiagnosticDto (
        @NotBlank(message = "La fecha de diagnostico es obligatoria")
        LocalDate dateDiagnostic,
        @NotBlank(message = "La descripción es obligatoria")
        String description,
        @NotBlank(message = "La gravedad es obligatoria")
        EnumGravedad gravedad,
        @NotBlank(message = "La fecha de control es obligatoria")
        LocalDate nextControlDate
    //Consulta consulta
    //Tratamiento tratamiento
){
}
