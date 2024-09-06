package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.pet.PetResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Pet;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Especie;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Sexo;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
  List<Pet> findAllByOwnerId(Long ownerId);

  List<Pet> findAllByRaceIgnoreCase(String race);

  List<Pet> findAllBySpecies(Especie especie);

  List<Pet> findAllBySex(Sexo sexo);

  List<Pet> findAllByCastrated(Boolean castrated);
}
