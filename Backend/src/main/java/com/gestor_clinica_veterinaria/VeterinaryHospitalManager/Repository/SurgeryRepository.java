package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurgeryRepository extends JpaRepository<Surgery, Long> {

    List<Surgery> findBySurgeryTypeContainingIgnoreCase(String surgeryType);
  //  List<Surgery> findByOwnerId(Long ownerId);
   // List<Surgery> findByPetId(Long petId);
  // Consulta para encontrar todas las cirugías por el ID de la mascota
  @Query("SELECT s FROM Surgery s WHERE s.diagnosis.consultation.pet.id = :petId")
  List<Surgery> findSurgeriesByPetId(@Param("petId") Long petId);
}
