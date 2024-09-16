package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.impl;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.ClinicHistoryResponseDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Consultation.ConsultationDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Consultation.ConsultationResponseDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Diagnosis.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Diagnosis.DiagnosticResponseDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.ResponseSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.*;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.ClinicHistoryRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.ClinicHistoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.ConsultationMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.DiagnosticMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.TreatmentMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.SurgeryMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.ComplementaryStudyMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.HospitalizationMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClinicHistoryServiceImpl implements ClinicHistoryService {

    private final ClinicHistoryRepository clinicHistoryRepository;
    private final ConsultationMapper consultationMapper;
    private final DiagnosticMapper diagnosticMapper;
    private final SurgeryMapper surgeryMapper;
    private final TreatmentMapper treatmentMapper;
  // @Override
  // public ClinicHistory getClinicHistoryByIdPet(Long id) {
  //     Optional<ClinicHistory> clinicHistoryOptional = clinicHistoryRepository.findByPet_Id(id);

  //     return clinicHistoryOptional.orElse(null);
  // }
  //  @Override
  //  public ClinicHistory getClinicHistoryByIdPet(Long id) {
  //      return clinicHistoryRepository.findByPet_Id(id).orElse(null);
  //  }
  @Override
  public ClinicHistory getClinicHistoryByIdPet(Long id) {
      System.out.println("Searching ClinicHistory for Pet ID: " + id);
      return clinicHistoryRepository.findByPet_Id(id).orElse(null);
  }

 //  @Transactional(readOnly = true)
 //  public ClinicHistoryResponseDto getClinicHistoryByPetId(Long petId) {
 //      // Obtener todas las entidades relacionadas con petId
 //      ClinicHistory clinicHistory = new ClinicHistory();

 //      // Obtener consultas
 //      List<ConsultationEntity> consultations = clinicHistoryRepository.findConsultationsByPetId(petId);
 //      clinicHistory.setConsultations(consultations);

 //      // Obtener diagnósticos
 //      List<DiagnosticEntity> diagnostics = clinicHistoryRepository.findDiagnosticsByPetId(petId);
 //      clinicHistory.setDiagnoses(diagnostics);

 //      // Obtener tratamientos
 //      List<Treatment> treatments = clinicHistoryRepository.findTreatmentsByPetId(petId);
 //      clinicHistory.setTreatments(treatments);

 //      // Obtener cirugías
 //      List<Surgery> surgeries = clinicHistoryRepository.findSurgeriesByPetId(petId);
 //      clinicHistory.setSurgeries(surgeries);

 //      // Obtener estudios complementarios
 //      List<ComplementaryStudy> complementaryStudies = clinicHistoryRepository.findComplementaryStudiesByPetId(petId);
 //      clinicHistory.setComplementaryStudies(complementaryStudies);

 //      // Obtener hospitalizaciones
 //      List<Hospitalization> hospitalizations = clinicHistoryRepository.findHospitalizationsByPetId(petId);
 //      clinicHistory.setHospitalizations(hospitalizations);

 //      // Mapear la entidad ClinicHistory a DTO
 //      return toDto(clinicHistory);
 //  }

 //  // Método de mapeo de ClinicHistory a ClinicHistoryDto
 //  private ClinicHistoryResponseDto toDto(ClinicHistory clinicHistory) {
 //      return new ClinicHistoryResponseDto(
 //              clinicHistory.getIdClinicHistory(),
 //              clinicHistory.getConsultations().stream()
 //                      .map(consultationMapper::toResponseDto)
 //                      .collect(Collectors.toList()),
 //              clinicHistory.getDiagnoses().stream()
 //                      .map(diagnosticMapper::toResponseDto)
 //                      .collect(Collectors.toList()),
 //              clinicHistory.getTreatments().stream()
 //                      .map(treatmentMapper::toDto)
 //                      .collect(Collectors.toList()),
 //              clinicHistory.getSurgeries().stream()
 //                      .map(surgeryMapper::toDto)
 //                      .collect(Collectors.toList())
 //      );
 //  }



}
