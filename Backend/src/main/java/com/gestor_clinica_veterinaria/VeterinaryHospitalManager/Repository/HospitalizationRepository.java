package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HospitalizationRepository extends JpaRepository<Hospitalization, Long> {

    List<Hospitalization> findByTreatments_Id(Long treatmentId);

    List<Hospitalization> findByComplementaryStudies_Id(Long complementaryStudyId);

    @Query("SELECT h FROM Hospitalization h JOIN h.complementaryStudies cs JOIN cs.consultation c WHERE c.pet.id = :petId")
    List<Hospitalization> findHospitalizationsByPetId(@Param("petId") Long petId);

    @Query("SELECT h FROM Hospitalization h JOIN h.treatments t JOIN t.diagnosis d JOIN d.consultation c WHERE c.pet.id = :petId")
    List<Hospitalization> findHospitalizationsByPetIdTreatment(@Param("petId") Long petId);
}
