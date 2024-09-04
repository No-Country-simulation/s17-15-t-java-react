package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HospitalizationRepository extends JpaRepository<Hospitalization, Long> {

    List<Hospitalization> findByTreatments_Id(Long treatmentId);

    List<Hospitalization> findByComplementaryStudies_Id(Long complementaryStudyId);
}
