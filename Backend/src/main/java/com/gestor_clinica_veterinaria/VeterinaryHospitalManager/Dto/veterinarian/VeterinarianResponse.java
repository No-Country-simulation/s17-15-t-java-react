package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.veterinarian;

public record VeterinarianResponse(
        Long id,
        String name,
        String lastname,
        String professionalLicenceNumber,
        String specialty
) {
}
