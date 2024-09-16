package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ClinicHistory;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiagnosticRepository extends JpaRepository<DiagnosticEntity, Long> {

    Page<DiagnosticEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<DiagnosticEntity> findByConsultationId(Long consultationId, Pageable pageable);
    // Método para buscar la historia clínica de una mascota usando su ID
// Método para buscar la historia clínica de una mascota usando su ID
    // Método que busca diagnósticos por el ID de la mascota (a través de consulta)
    @Query("SELECT d FROM DiagnosticEntity d WHERE d.consultation.pet.id = :petId")
    List<DiagnosticEntity> findAllByPetId(@Param("petId") Long petId);
}
