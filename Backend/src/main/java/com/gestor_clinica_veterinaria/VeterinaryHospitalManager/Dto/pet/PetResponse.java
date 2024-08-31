package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Especie;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Sexo;
import java.time.LocalDate;

public record PetResponse(
    Long id,
    String name,
    String race,
    Especie species,
    LocalDate birthdate,
    Sexo sex,
    String allergies,
    Boolean castrated,
    Boolean alive,
    String details,
    @JsonProperty(value = "owner_id")
    Long ownerId
) {

}
