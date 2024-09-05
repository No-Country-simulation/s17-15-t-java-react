package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {

    List<Veterinarian> findAllBySpecialtyIgnoreCase(String specialty);

    List<Veterinarian> findAllByUsernameIgnoreCase(String Username);

    List<Veterinarian> findAllByLastNameIgnoreCase(String lastName);

    Optional<Veterinarian> findByUsername(String username);
    Optional<Veterinarian> findByProfessionalLicenceNumber(String professionalLicenceNumber);

}
