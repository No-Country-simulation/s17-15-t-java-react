package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study.ComplementaryStudy;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study.EnumStudyState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComplementaryStudyRepository extends JpaRepository<ComplementaryStudy, Long> {
    List<ComplementaryStudy> findStudyByState(EnumStudyState studyState);
}
