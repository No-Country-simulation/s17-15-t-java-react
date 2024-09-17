package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Treatment;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TreatmentMapper {

    public Treatment toEntity(TreatmentRequest dto, DiagnosticEntity diagnosis){
        Treatment treatment = new Treatment();

        treatment.setTreatmentDescription(dto.treatmentDescription());
        treatment.setDuration(dto.duration());
        treatment.setAdditionalObservations(dto.aditionalObservations());
        treatment.setTreatmentCost(dto.treatmentCost());
        treatment.setDiagnosis(diagnosis);

        return treatment;
    }

    public TreatmentRequest toDto(Treatment entity) {
        return new TreatmentRequest(
                entity.getTreatmentDescription(),
                entity.getDuration(),
                entity.getAdditionalObservations(),
                entity.getTreatmentCost(),
                entity.getDiagnosis().getId()
        );
    }


    public List<TreatmentRequest> toDtoList(List<Treatment> treatmentList) {
        return treatmentList.stream().map(this::toDto).toList();
    }

   // public TreatmentDto convertToDto(Treatment treatment) {
   //     return new TreatmentDto(
   //             treatment.getId(),
   //             treatment.getTreatmentDescription(),
   //             treatment.getDuration(),
   //             treatment.getAdditionalObservations(),
   //             treatment.getTreatmentCost(),
   //             treatment.getDiagnosis() != null ? treatment.getDiagnosis().getId() : null,
   //             treatment.getHospitalization() != null ? treatment.getHospitalization().getId() : null
   //     );
   // }
}
