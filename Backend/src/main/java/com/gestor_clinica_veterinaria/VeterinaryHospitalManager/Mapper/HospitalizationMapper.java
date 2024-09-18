package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.hospitalization.HospitalizationRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.hospitalization.HospitalizationResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Treatment;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HospitalizationMapper {

    public Hospitalization toEntity(HospitalizationRequest dto, Treatment treatment){
        Hospitalization hospitalization = new Hospitalization();

        hospitalization.setStartDate(dto.startDate());
        hospitalization.setEnd_date(dto.endDate());
        hospitalization.setPaid(dto.paid());
        hospitalization.setHospitalizationCost(dto.hospitalizationCost());
        hospitalization.getTreatments().add(treatment);

        return hospitalization;
    }
    public HospitalizationRequest toDto(Hospitalization entity) {
        return new HospitalizationRequest(
                entity.getStartDate(),
                entity.getEnd_date(),
                entity.getHospitalizationCost(),
                entity.isPaid(),
                entity.getTreatments() != null && !entity.getTreatments().isEmpty() ?
                        entity.getTreatments().get(0).getId() : null
        );
    }
    public HospitalizationResponse toDtoResponse(Hospitalization entity) {
        return new HospitalizationResponse(
                entity.getId(),
                entity.getStartDate(),
                entity.getEnd_date(),
                entity.getHospitalizationCost(),
                entity.isPaid(),
                entity.getComplementaryStudies(),
                entity.getTreatments()
        );
    }

    public List<HospitalizationResponse> toDtoList(List<Hospitalization> hospitalizationList) {
        return hospitalizationList.stream()
                .map(hospitalization -> toDtoResponse(hospitalization))
                .collect(Collectors.toList());
    }
}
