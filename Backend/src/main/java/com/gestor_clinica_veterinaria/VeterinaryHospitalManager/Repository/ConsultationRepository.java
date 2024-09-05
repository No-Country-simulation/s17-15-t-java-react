package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ConsultationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<ConsultationEntity, Long> {
    Page<ConsultationEntity> findByNameContainingIgnoreCase(String query, PageRequest id);

    Page<ConsultationEntity> findByPetId(Long petId, Pageable pageable);

    Page<ConsultationEntity> findByVeterinarian(Long vetId, Pageable pageable);
}
