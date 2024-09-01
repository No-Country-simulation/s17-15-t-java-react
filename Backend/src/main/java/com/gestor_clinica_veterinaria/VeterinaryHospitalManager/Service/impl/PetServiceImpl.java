package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.impl;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.pet.PetRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.pet.PetResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Owner;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Pet;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Exceptions.ResourceNotFoundException;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.PetMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.OwnerRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.PetRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.PetService;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Especie;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Sexo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PetServiceImpl implements PetService {

  private final PetRepository petRepository;
  private final OwnerRepository ownerRepository;

  @Autowired
  public PetServiceImpl(PetRepository petRepository, OwnerRepository ownerRepository) {
    this.petRepository = petRepository;
    this.ownerRepository = ownerRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public List<PetResponse> getAllPets() {
    return PetMapper.toListDto(petRepository.findAll());
  }

  @Transactional(readOnly = true)
  @Override
  public List<PetResponse> getAllPetsByOwnerId(Long ownerId) {
    return PetMapper.toListDto(petRepository.findAllByOwnerId(ownerId));
  }

  @Transactional(readOnly = true)
  @Override
  public List<PetResponse> getAllPetsByRace(String race) {
    return PetMapper.toListDto(petRepository.findAllByRaceIgnoreCase(race));
  }

  @Transactional(readOnly = true)
  @Override
  public List<PetResponse> getAllPetsByEspecies(String especie) {
    return PetMapper.toListDto(
        petRepository.findAllBySpecies(Especie.valueOf(especie.toUpperCase())));
  }

  @Transactional(readOnly = true)
  @Override
  public List<PetResponse> getAllPetsBySex(String sex) {
    return PetMapper.toListDto(petRepository.findAllBySex(Sexo.valueOf(sex.toUpperCase())));
  }

  @Transactional(readOnly = true)
  @Override
  public List<PetResponse> getAllPetsByCastrated(Boolean castrated) {
    return PetMapper.toListDto(petRepository.findAllByCastrated(castrated));
  }

  @Transactional(readOnly = true)
  @Override
  public PetResponse getPetById(Long id) {
    Pet pet = petRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("La mascota con el " + id + " no existe"));
    return PetMapper.toDto(pet);
  }

  @Override
  public PetResponse savePet(PetRequest petRequest) {
    Owner ownerExists = ownerRepository.findById(petRequest.ownerId())
        .orElseThrow(() -> new ResourceNotFoundException("El ownerId no esta asociado a ningun "
            + "Propietario"));
    Pet pet = PetMapper.toEntity(petRequest, ownerExists);
    Pet petCreated = petRepository.save(pet);
    return PetMapper.toDto(petCreated);
  }

  @Override
  public PetResponse updatePet(Long id, PetRequest petRequest) {
    Owner ownerExists = ownerRepository.findById(petRequest.ownerId())
        .orElseThrow(() -> new ResourceNotFoundException("El ownerId no esta asociado a ningun "
            + "Propietario"));
    Pet oldPet = petRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("La mascota con el " + id + " no existe"));
    PetMapper.updateEntity(oldPet, petRequest, ownerExists);
    Pet petUpdated = petRepository.save(oldPet);
    return PetMapper.toDto(petUpdated);
  }

  @Override
  public void deletePet(Long id) {
    Pet pet = petRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("La mascota con el " + id + " no existe"));
    petRepository.delete(pet);
  }
}
