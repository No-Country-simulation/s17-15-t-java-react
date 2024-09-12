package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Treatment;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class TreatmentMapper {

    public Treatment toEntity(TreatmentRequest dto){
        Treatment treatment = new Treatment();
        treatment.setTreatmentDescription(dto.treatmentDescription());
        treatment.setDuration(dto.duration());
        treatment.setAdditionalObservations(dto.aditionalObservations());
        treatment.setTreatmentCost(dto.treatmentCost());
        if (dto.hospitalizationId() != null) {
            Hospitalization hospitalization = new Hospitalization();
            hospitalization.setId(dto.hospitalizationId().get());
            treatment.setHospitalization(hospitalization);
        }
        return treatment;
    }

    public TreatmentRequest toDto(Treatment entity) {
        return new TreatmentRequest(
                entity.getTreatmentDescription(),
                entity.getDuration(),
                entity.getAdditionalObservations(),
                entity.getTreatmentCost(),
                Optional.ofNullable(entity.getDiagnosis() != null ? entity.getDiagnosis().getId() : null),
                Optional.ofNullable(entity.getHospitalization() != null ? entity.getHospitalization().getId() : null)
        );
    }


    public List<TreatmentRequest> toDtoList(List<Treatment> treatmentList) {
        return treatmentList.stream().map(this::toDto).toList();
    }
}
