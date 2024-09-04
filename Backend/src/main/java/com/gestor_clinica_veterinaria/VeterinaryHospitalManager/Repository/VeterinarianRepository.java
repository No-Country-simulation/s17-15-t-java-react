package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {

    List<Veterinarian> findAllBySpecialtyIgnoreCase(String specialty);

    List<Veterinarian> findAllByNameIgnoreCase(String name);

    List<Veterinarian> findAllByLastNameIgnoreCase(String lastName);

}
