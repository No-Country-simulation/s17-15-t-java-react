package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Consultation.ConsultationDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ComplementaryStudy;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ConsultationEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.ConsultationMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.ComplementaryStudyRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.ConsultationRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.DiagnosticRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;
    private final DiagnosticRepository diagnosticRepository;
    private final ComplementaryStudyRepository complementaryStudyRepository;
    private final OwnerRepository ownerRepository;


    @Transactional
    public ConsultationDto addConsultation(ConsultationDto dto) {
        try{
            ConsultationEntity entity = consultationMapper.toEntity(dto);
            ConsultationEntity savedEntity = consultationRepository.save(entity);
            return consultationMapper.toDto(savedEntity);
        }catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("Error de integridad de datos al crear la consulta");
        }catch (Exception e){
            throw new IllegalArgumentException("Error al crear la consulta");
        }
    }

    @Transactional
    public ConsultationDto updateConsultation(Long id, ConsultationDto dto) {
        ConsultationEntity consultationEntity = consultationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada con ID: " + id));
        try{
            consultationEntity.setAnamnesis(dto.name());
            consultationEntity.setConsultationDate(dto.consultationDate());
            consultationEntity.setObservations(dto.observations());
            consultationEntity.setState(dto.state());
            consultationEntity.setCostConsultation(dto.costConsultation());

            ConsultationEntity savedEntity = consultationRepository.save(consultationEntity);
            return consultationMapper.toDto(savedEntity);
        }catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("Error de integridad de datos al actualizar la consulta");
        }catch (Exception e){
            throw new IllegalArgumentException("Error al actualizar la consulta");
        }
    }

    public ConsultationDto getConsultationById(Long id) {
        ConsultationEntity consultationEntity = consultationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada con ID: " + id));
        return consultationMapper.toDto(consultationEntity);
    }

    public Page<ConsultationDto> getAllConsultations(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid page or size parameters");
        }
        Page<ConsultationEntity> consultationPage = consultationRepository.findAll(PageRequest.of(page, size, Sort.by("id")));
        return consultationPage.map(consultationMapper::toDto);
    }

    public Page<ConsultationDto> searchConsultations(int page, int size, String query) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid page or size parameters");
        }
        Page<ConsultationEntity> consultationPage = consultationRepository.findByNameContainingIgnoreCase(query, PageRequest.of(page, size, Sort.by("name")));
        return consultationPage.map(consultationMapper::toDto);
    }

    public ConsultationDto getConsultationByDiagnosisId(Long diagnosisId) {
        DiagnosticEntity diagnosis = diagnosticRepository.findById(diagnosisId)
                .orElseThrow(() -> new EntityNotFoundException("Diagnostico no encontrado con ID: " + diagnosisId));

        ConsultationEntity consultation = diagnosis.getConsultation();
        return consultationMapper.toDto(consultation);
    }

    public ConsultationDto getConsultationByComplementaryStudyId(Long complementaryStudyId) {
        ComplementaryStudy complementaryStudy = complementaryStudyRepository.findById(complementaryStudyId)
                .orElseThrow(() -> new EntityNotFoundException("Estudio complementario no encontrado con ID: " + complementaryStudyId));

        ConsultationEntity consultation = complementaryStudy.getConsultation();
        return consultationMapper.toDto(consultation);
    }

    public Page<ConsultationDto> getConsultationsByPetId(int page, int size, Long petId) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid page or size parameters");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<ConsultationEntity> consultationPage = consultationRepository.findByPetId(petId, pageable);
        return consultationPage.map(consultationMapper::toDto);
    }

    public  Page<ConsultationDto> getConsultationsByVeterinaryId(int page, int size, Long vetId) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid page or size parameters");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<ConsultationEntity> consultationPage = consultationRepository.findByVeterinarian(vetId, pageable);
        return consultationPage.map(consultationMapper::toDto);
    }




}
