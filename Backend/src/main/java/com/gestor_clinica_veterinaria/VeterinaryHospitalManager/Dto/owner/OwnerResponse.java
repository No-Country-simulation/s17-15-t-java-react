package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.pet.PetResponse;
import java.util.List;

public record OwnerResponse(
    Long id,
    String name,
    String lastname,
    String phone,
    String email,
    String address
) {

}
