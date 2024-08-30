package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.pet.PetRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.pet.PetResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Owner;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Pet;
import java.util.List;

public class PetMapper {

  public static PetResponse toDto(Pet pet) {
    if (pet == null) {
      return null;
    }

    return new PetResponse(
        pet.getId(),
        pet.getName(),
        pet.getRace(),
        pet.getSpecies(),
        pet.getBirthdate(),
        pet.getSex(),
        pet.getAllergies(),
        pet.getCastrated(),
        pet.getAlive(),
        pet.getDetails(),
        pet.getOwner()
    );
  }

  public static List<PetResponse> toListDto(List<Pet> petList) {
    if (petList == null) {
      return null;
    }

    return petList
        .stream()
        .map(PetMapper::toDto)
        .toList();
  }

  public static Pet toEntity(PetRequest petRequest, Owner owner) {
    if (petRequest == null) {
      return null;
    }

    Pet pet = new Pet();
    pet.setName(petRequest.name());
    pet.setRace(petRequest.race());
    pet.setSpecies(petRequest.species());
    pet.setBirthdate(petRequest.birthdate());
    pet.setSex(petRequest.sex());
    pet.setAllergies(petRequest.allergies());
    pet.setCastrated(petRequest.castrated());
    pet.setAlive(petRequest.alive());
    pet.setDetails(petRequest.details());
    pet.setOwner(owner);

    return pet;
  }

  public static void updateEntity(Pet oldPet, PetRequest petRequest, Owner owner) {
    if (oldPet == null || petRequest == null || owner == null) {
      return;
    }

    oldPet.setName(petRequest.name());
    oldPet.setRace(petRequest.race());
    oldPet.setSpecies(petRequest.species());
    oldPet.setBirthdate(petRequest.birthdate());
    oldPet.setSex(petRequest.sex());
    oldPet.setAllergies(petRequest.allergies());
    oldPet.setCastrated(petRequest.castrated());
    oldPet.setAlive(petRequest.alive());
    oldPet.setDetails(petRequest.details());
    oldPet.setOwner(owner);
  }
}
