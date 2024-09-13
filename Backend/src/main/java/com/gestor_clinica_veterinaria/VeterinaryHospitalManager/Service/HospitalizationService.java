package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.hospitalization.HospitalizationCreationResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.hospitalization.HospitalizationRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Hospitalization;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Treatment;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.HospitalizationMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.HospitalizationRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.TreatmentRepository;
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

    public HospitalizationCreationResponse addHospitalization(HospitalizationRequest hospitalizationRequest){
        Hospitalization hospitalization = hospitalizationMapper.toEntity(hospitalizationRequest);

        if (hospitalization.getStartDate() == null) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser nula.");
        }
        if (hospitalization.getEnd_date() != null && hospitalization.getEnd_date().isBefore(hospitalization.getStartDate())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        if (hospitalization.getEnd_date() != null) {
            hospitalization.setHospitalizationCost(calculateCost(hospitalization.getStartDate(), hospitalization.getEnd_date()));
        } else {
            hospitalization.setHospitalizationCost(BigDecimal.ZERO);
        }

        hospitalization = hospitalizationRepository.save(hospitalization);

        return new HospitalizationCreationResponse("¡La hospitalización se creó exitosamente!", hospitalization.getId() );
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

    public Hospitalization updateHospitalization(Long hospitalizationId, HospitalizationRequest dto){

        Hospitalization hospitalization = hospitalizationRepository.findById(hospitalizationId)
                .orElseThrow(() -> new EntityNotFoundException("No se ha podido actualizar la hospitalización porque el id ingresado es incorrecto o no existe: " + hospitalizationId));

        hospitalization.setStartDate(dto.startDate());
        hospitalization.setEnd_date(dto.endDate());
        hospitalization.setHospitalizationCost(dto.hospitalizationCost());

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