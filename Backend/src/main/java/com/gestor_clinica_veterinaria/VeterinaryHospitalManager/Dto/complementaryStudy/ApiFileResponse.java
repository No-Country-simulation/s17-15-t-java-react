package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy;

public record ApiFileResponse(
        boolean success,
        String message,
        Object data
) {
}
