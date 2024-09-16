package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Optional;

public record TreatmentRequest(
        @NotBlank(message = "debe dar una descipción al tratamiento.")
        @Size(max = 500, message = "la descripción del tratamiento no debe tener más de 500 caracteres.")
        String treatmentDescription,
        @NotBlank(message = "debe dar una duración al tratamiento.")
        @Size(max = 50, message = "la duración del tratamiento no debe tener más de 50 caracteres.")
        String duration,
        @Size(max = 200, message = "las observaciones adicionales del tratamiento no debe tener más de 200 caracteres.")
        String aditionalObservations,

        BigDecimal treatmentCost,
        @NotNull(message = "Debe proporcionar un Id de diagnostico")
        Long  diagnosisId
) {
}