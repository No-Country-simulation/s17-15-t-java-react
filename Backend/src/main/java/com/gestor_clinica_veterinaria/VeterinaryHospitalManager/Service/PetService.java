package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.pet.PetRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.pet.PetResponse;
import java.util.List;

public interface PetService {

  List<PetResponse> getAllPets();

  List<PetResponse> getAllPetsByOwnerId(Long ownerId);

  List<PetResponse> getAllPetsByRace(String race);

  List<PetResponse> getAllPetsByEspecies(String especie);

  List<PetResponse> getAllPetsBySex(String sex);

  List<PetResponse> getAllPetsByCastrated(Boolean castrated);

  PetResponse getPetById(Long id);

  PetResponse savePet(PetRequest petRequest);

  PetResponse updatePet(Long id, PetRequest petRequest);

  void deletePet(Long id);
}
