package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Treatment;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<TreatmentResponse> toDtoList(List<Treatment> treatments) {
        return treatments.stream().map(treatment->toDtoResponse(treatment)).collect(Collectors.toList());
    }

    public TreatmentResponse toDtoResponse(Treatment entity) {
        DiagnosticEntity diagnosis = Hibernate.isInitialized(entity.getDiagnosis())
                ? entity.getDiagnosis()
                : null;

        // Verifica si la hospitalización está inicializada
        Hospitalization hospitalization = Hibernate.isInitialized(entity.getHospitalization())
                ? entity.getHospitalization()
                : null;

        return new TreatmentResponse(
                entity.getId(),
                entity.getTreatmentDescription(),
                entity.getDuration(),
                entity.getAdditionalObservations(),
                entity.getTreatmentCost(),
                diagnosis,  // Entrega diagnosis inicializado o null
                hospitalization  // Entrega hospitalization inicializado o null
        );
    }
}
