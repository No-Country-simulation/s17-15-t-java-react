package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicHistoryRepository extends JpaRepository <ClinicHistory, Long> {

    Optional<ClinicHistory> findByPet_Id (Long petId);
    @Query("SELECT d FROM DiagnosticEntity d WHERE d.consultation.pet.id = :petId")
    List<DiagnosticEntity> findAllByPetId(@Param("petId") Long petId);




    @Query("SELECT c FROM ConsultationEntity c WHERE c.pet.id = :petId")
    List<ConsultationEntity> findConsultationsByPetId(@Param("petId") Long petId);

    @Query("SELECT d FROM DiagnosticEntity d WHERE d.consultation.pet.id = :petId")
    List<DiagnosticEntity> findDiagnosticsByPetId(@Param("petId") Long petId);

    @Query("SELECT t FROM Treatment t WHERE t.diagnosis.consultation.pet.id = :petId")
    List<Treatment> findTreatmentsByPetId(@Param("petId") Long petId);

    @Query("SELECT s FROM Surgery s WHERE s.diagnosis.consultation.pet.id = :petId")
    List<Surgery> findSurgeriesByPetId(@Param("petId") Long petId);

    @Query("SELECT cs FROM ComplementaryStudy cs WHERE cs.consultation.pet.id = :petId")
    List<ComplementaryStudy> findComplementaryStudiesByPetId(@Param("petId") Long petId);

    @Query("SELECT h FROM Hospitalization h JOIN h.complementaryStudies cs JOIN cs.consultation c WHERE c.pet.id = :petId")
    List<Hospitalization> findHospitalizationsByPetId(@Param("petId") Long petId);
}
