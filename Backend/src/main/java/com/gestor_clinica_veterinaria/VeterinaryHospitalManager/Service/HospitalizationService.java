package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.hospitalization.HospitalizationDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.HospitalizationMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.HospitalizationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalizationService {

    private static final BigDecimal DAILY_RATE = BigDecimal.valueOf(85.0);
    private final HospitalizationRepository hospitalizationRepository;
    private final HospitalizationMapper hospitalizationMapper;

    public Hospitalization addHospitalization(HospitalizationDto dto){
        Hospitalization hospitalization = hospitalizationMapper.toEntity(dto);

        hospitalization.setHospitalizationCost(calculateCost(hospitalization.getStartDate(), hospitalization.getEnd_date()));

        hospitalization = hospitalizationRepository.save(hospitalization);
        return hospitalization;
    }

    public List<Hospitalization> getAllHospitalizations(){
        return hospitalizationRepository.findAll();
    }

    public Hospitalization getHospitalizationById(Long hospitalizationId){
        return hospitalizationRepository.findById(hospitalizationId)
                .orElseThrow(() -> new EntityNotFoundException("El id de hospitalización ingresado es incorrecto o no existe."));
    }
    public List<Hospitalization> getHospitalizationByTreatment(Long treatmentId) {
        return hospitalizationRepository.findByTreatments_Id(treatmentId);
    }

    public List<Hospitalization> getHospitalizationByComplementaryStudy(Long complementaryStudyId) {
        return hospitalizationRepository.findByComplementaryStudies_Id(complementaryStudyId);
    }

    public Hospitalization updateHospitalization(Long hospitalizationId, HospitalizationDto dto){

        Hospitalization hospitalization = hospitalizationRepository.findById(hospitalizationId)
                .orElseThrow(() -> new EntityNotFoundException("No se ha podido actualizar la hospitalización porque el id ingresado es incorrecto o no existe: " + hospitalizationId));

        if (dto.startDate() != null) {
            hospitalization.setStartDate(dto.startDate());
        }
        if (dto.endDate() != null) {
            hospitalization.setEnd_date(dto.endDate());
        }
        if (dto.hospitalizationCost() != null) {
            hospitalization.setHospitalizationCost(dto.hospitalizationCost());
        }
        hospitalization.setPaid(dto.paid());

        if (dto.startDate() != null && dto.endDate() != null) {
            hospitalization.setHospitalizationCost(calculateCost(dto.startDate(), dto.endDate()));
        }

        return hospitalizationRepository.save(hospitalization);
    }

    private BigDecimal calculateCost(LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null && !endDate.isBefore(startDate)) {
            long days = ChronoUnit.DAYS.between(startDate, endDate);
            return DAILY_RATE.multiply(BigDecimal.valueOf(days)).setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }
}