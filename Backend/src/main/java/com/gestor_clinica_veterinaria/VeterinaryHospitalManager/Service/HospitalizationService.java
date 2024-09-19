package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.hospitalization.HospitalizationCreationResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.hospitalization.HospitalizationDtoResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.hospitalization.HospitalizationRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.hospitalization.HospitalizationResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ComplementaryStudy;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalizationService {

    private static final BigDecimal DAILY_RATE = BigDecimal.valueOf(85.0);
    private final HospitalizationRepository hospitalizationRepository;
    private final HospitalizationMapper hospitalizationMapper;
    private final TreatmentRepository treatmentRepository;

    public HospitalizationCreationResponse addHospitalization(HospitalizationRequest hospitalizationRequest){

        try {
            Treatment treatment = treatmentRepository.findById(hospitalizationRequest.treatmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Treatment not found with id: " + hospitalizationRequest.treatmentId()));

            Hospitalization hospitalization = hospitalizationMapper.toEntity(hospitalizationRequest, treatment);
            if (hospitalization.getTreatments() == null) {
                hospitalization.setTreatments(new ArrayList<>());
            }
            hospitalization.getTreatments().add(treatment);

            treatment.setHospitalization(hospitalization);

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

        } catch (EntityNotFoundException e) {
            return new HospitalizationCreationResponse("Error Entity: " + e.getMessage(), null);
        } catch (IllegalArgumentException e) {
            return new HospitalizationCreationResponse("Error Arguments: " + e.getMessage(), null);
        } catch (Exception e) {
            return new HospitalizationCreationResponse("Error Exception: " + e.getMessage(), null);
        }
    }

    public List<HospitalizationResponse> getAllHospitalizations(){
        return hospitalizationMapper.toDtoList(hospitalizationRepository.findAll());
    }

    public HospitalizationResponse getHospitalizationById(Long hospitalizationId){
            Hospitalization entity =  hospitalizationRepository.findById(hospitalizationId).orElseThrow(() -> new EntityNotFoundException("El id de hospitalización ingresado es incorrecto o no existe."));
            HospitalizationResponse response = hospitalizationMapper.toDtoResponse(entity);
        return response;

    }
    public List<HospitalizationResponse> getHospitalizationByTreatment(Long treatmentId) {
        return hospitalizationMapper.toDtoList(hospitalizationRepository.findByTreatments_Id(treatmentId));
    }

    public List<HospitalizationResponse> getHospitalizationByComplementaryStudy(Long complementaryStudyId) {
        return hospitalizationMapper.toDtoList(hospitalizationRepository.findByComplementaryStudies_Id(complementaryStudyId));
    }

    public HospitalizationResponse updateHospitalization(Long hospitalizationId, HospitalizationRequest dto){

        Hospitalization hospitalization = hospitalizationRepository.findById(hospitalizationId)
                .orElseThrow(() -> new EntityNotFoundException("No se ha podido actualizar la hospitalización porque el id ingresado es incorrecto o no existe: " + hospitalizationId));

        hospitalization.setStartDate(dto.startDate());
        hospitalization.setEnd_date(dto.endDate());
        hospitalization.setHospitalizationCost(dto.hospitalizationCost());

        hospitalization.setPaid(dto.paid());

        if (dto.startDate() != null && dto.endDate() != null) {
            hospitalization.setHospitalizationCost(calculateCost(dto.startDate(), dto.endDate()));
        }
        hospitalizationRepository.save(hospitalization);

        return hospitalizationMapper.toDtoResponse(hospitalization);
    }

    private BigDecimal calculateCost(LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null && !endDate.isBefore(startDate)) {
            long days = ChronoUnit.DAYS.between(startDate, endDate);
            return DAILY_RATE.multiply(BigDecimal.valueOf(days)).setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    // Convertir entidad Hospitalization a DTO
    private HospitalizationDtoResponse convertToDto(Hospitalization hospitalization) {
        return new HospitalizationDtoResponse(
                hospitalization.getId(),
                hospitalization.getStartDate(),
                hospitalization.getEnd_date(), // Usar el nombre del atributo en la entidad
                hospitalization.getHospitalizationCost(),
                hospitalization.isPaid(),
                hospitalization.getTreatments().stream()
                        .map(Treatment::getId)
                        .toList(), // Usar Stream.toList()
                hospitalization.getComplementaryStudies().stream()
                        .map(ComplementaryStudy::getId) // Asegúrate de que `getId()` exista
                        .toList() // Usar Stream.toList()
        );
    }
    // Obtener hospitalizaciones por ID de mascota y devolver como DTO
    //  public List<HospitalizationDtoResponse> getHospitalizationsByPetId(Long petId) {
    //      List<Hospitalization> hospitalizations = hospitalizationRepository.findHospitalizationsByPetId(petId);
    //      return hospitalizations.stream()
    //              .map(this::convertToDto)
    //              .collect(Collectors.toList());
    //  }

    public List<HospitalizationDtoResponse> getHospitalizationsByPetId(Long petId) {
        // Obtener hospitalizaciones desde ComplementaryStudies
        List<Hospitalization> hospitalizationsByStudies = hospitalizationRepository.findHospitalizationsByPetId(petId);

        // Obtener hospitalizaciones desde Treatment y Diagnosis
        List<Hospitalization> hospitalizationsByTreatment = hospitalizationRepository.findHospitalizationsByPetIdTreatment(petId);

        // Unir ambas listas y eliminar duplicados
        Set<Hospitalization> combinedHospitalizations = new HashSet<>(hospitalizationsByStudies);
        combinedHospitalizations.addAll(hospitalizationsByTreatment);

        // Convertir a DTO
        return combinedHospitalizations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}