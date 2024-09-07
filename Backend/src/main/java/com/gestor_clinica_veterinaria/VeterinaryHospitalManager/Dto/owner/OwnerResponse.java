package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner;

public record OwnerResponse(
    Long id,
    String name,
    String lastname,
    String phone,
    String email,
    String address
) {

}