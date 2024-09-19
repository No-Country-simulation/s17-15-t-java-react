package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ComplementaryStudy;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumStudyState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComplementaryStudyRepository extends JpaRepository<ComplementaryStudy, Long> {
    List<ComplementaryStudy> findByStudyState(EnumStudyState studyState);

    @Query("SELECT cs FROM ComplementaryStudy cs WHERE cs.consultation.pet.id = :petId")
    List<ComplementaryStudy> findAllByPetId(@Param("petId") Long petId);
  //  @Query("SELECT cs FROM ComplementaryStudy cs " +
  //          "JOIN cs.consultation c " +
  //          "JOIN c.pet p " +
  //          "WHERE p.id = :petId")
  //  List<ComplementaryStudy> findAllByPetId(@Param("petId") Long petId);
//
   // @Query("SELECT cs FROM ComplementaryStudy cs " +
   //         "JOIN cs.consultation c " +
   //         "WHERE c.pet.id = :petId")
   // List<ComplementaryStudy> findAllByPetId(@Param("petId") Long petId);

}
