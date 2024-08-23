package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.EnumGravedad;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public record DiagnosticDto (
    LocalDate dateDiagnostic,
    String description,
    EnumGravedad gravedad,
    LocalDate nextControlDate
    //Consulta consulta
    //Tratamiento tratamiento
){
}
