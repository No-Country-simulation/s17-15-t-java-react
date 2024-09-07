package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumSexPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record PetRequest(
    @NotBlank(message = "El name no puede ir vacio")
    @Size(min = 3)
    String name,

    @NotBlank(message = "El race no puede ir vacio")
    @Size(min = 2)
    String race,

    @NotBlank(message = "El campo especies no puede ir vacio")
    @Size(min = 2)
    String species,

    @NotNull(message = "El campo birthdate no puede ir vacio")
    @Past(message = "El campo birthdate debe ser pasada")
    LocalDate birthdate,


    @NotNull(message = "El campo sex no puede ir vacio")
    EnumSexPet sex,

    @NotBlank(message = "El campo allergies no puede ir vacio")
    @Size(min = 10, max = 100)
    String allergies,

    @NotNull(message = "El campo castrated no puede ir vacio y deber ser true o false")
    Boolean castrated,

    @NotNull(message = "El campo active no puede ir vacio")
    Boolean active,

    @Size(min = 10, max = 255, message = "El campo details debe tener una longitud de 10 a 255 "
        + "caracteres")
    String details,

    @JsonProperty(value = "owner_id")
    @NotNull(message = "el campo owner_id no puede ir vacio")
    Long ownerId
) {

}
