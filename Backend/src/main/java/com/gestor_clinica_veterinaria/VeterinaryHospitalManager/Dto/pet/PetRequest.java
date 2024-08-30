package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Especie;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record PetRequest(
    @NotBlank(message = "El name no puede ir vacio")
    @Size(min = 8, max = 45)
    String name,

    @NotBlank(message = "El race no puede ir vacio")
    @Size(min = 8, max = 45)
    String race,

    @NotBlank(message = "El campo especies no puede ir vacio")
    Especie species,

    @NotBlank(message = "El campo birthdate no puede ir vacio")
    @Past(message = "El campo birthdate debe ser pasada")
    LocalDate birthdate,

    @NotBlank(message = "El campo sex no puede ir vacio")
    Sexo sex,

    @NotBlank(message = "El campo allergies no puede ir vacio")
    @Size(min = 10, max = 100)
    String allergies,

    @NotBlank(message = "El campo castrated no puede ir vacio")
    Boolean castrated,

    @NotBlank(message = "El campo alive no puede ir vacio")
    Boolean alive,

    @Size(min = 10, max = 255, message = "El campo details debe tener una longitud de 10 a 255 "
        + "caracteres")
    String details,

    @JsonProperty(value = "owner_id")
    @NotNull(message = "el campo owner_id no puede ir vacio")
    Long ownerId
) {

}
