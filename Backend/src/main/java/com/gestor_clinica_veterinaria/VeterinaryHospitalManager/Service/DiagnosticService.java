package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Diagnosis.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Diagnosis.DiagnosticResponseDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.*;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Exceptions.DiagnosticNotFoundException;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.DiagnosticMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.*;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Exceptions.ComplementaryStudyNotFoundException;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Exceptions.SurgeryNotFoundException;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Exceptions.TreatmentNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class DiagnosticService {

    private final DiagnosticRepository diagnosticRepository;
    private final TreatmentRepository treatmentRepository;
    private final DiagnosticMapper diagnosticMapper;
    private final ConsultationRepository consultationRepository;
    private final SurgeryRepository surgeryRepository;
    private final ComplementaryStudyRepository complementaryStudyRepository;

    private final PetRepository petRepository;


    @Transactional
    public DiagnosticResponseDto addDiagnostic(@Valid DiagnosticDto dto) {
            ConsultationEntity consultationEntity = consultationRepository.findById(dto.consulta_id())
                    .orElseThrow(() -> new IllegalArgumentException("Consulta no encontrada"));
            DiagnosticEntity diagnostico = diagnosticMapper.toEntity(dto);

            diagnostico = diagnosticRepository.save(diagnostico);

            consultationEntity.getDiagnostics().add(diagnostico);
            return diagnosticMapper.toResponseDto(diagnostico);
    }

    @Transactional(readOnly = true)
    public Page<DiagnosticResponseDto> getAllDiagnostics(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid page or size parameters");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<DiagnosticEntity> diagnosticPage = diagnosticRepository.findAll(pageable);

        return diagnosticPage.map(diagnosticMapper::toResponseDto);
    }
    @Transactional(readOnly = true)
    public DiagnosticDto getDiagnosticById(Long id) {
        DiagnosticEntity diagnostic = diagnosticRepository.findById(id)
                .orElseThrow(() -> new DiagnosticNotFoundException("El diagnostico no existe"));
        return diagnosticMapper.toDto(diagnostic);
    }

    @Transactional
    public DiagnosticDto updateDiagnostic(Long id, @Valid DiagnosticDto dto) {

        try {
            DiagnosticEntity diagnostic = diagnosticRepository.findById(id)
                    .orElseThrow(() -> new DiagnosticNotFoundException("El diagnóstico no se puede actualizar porque no existe"));
            diagnostic.setName(dto.name());
            diagnostic.setDiagnosisDate(dto.diagnosisDate());
            diagnostic.setDescription(dto.description());
            diagnostic.setSeveridad(dto.severidad());
            diagnostic.setNextCheckUp(dto.nextCheckUp());

            diagnostic = diagnosticRepository.save(diagnostic);
            return diagnosticMapper.toDto(diagnostic);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error de integridad de datos al actualizar el diagnóstico", e);
        }catch (Exception e) {
            throw new RuntimeException("Error inesperado al actualizar el diagnóstico", e);
        }
    }

    @Transactional
    public void deleteDiagnostic(Long id) {
        try {
            diagnosticRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new DiagnosticNotFoundException("El diagnóstico no se puede eliminar porque no existe" + ex);
        }
    }

    @Transactional(readOnly = true)
    public Page<DiagnosticResponseDto> searchDiagnostics(int page, int size, String query) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid page or size parameters");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<DiagnosticEntity> diagnosticPage = diagnosticRepository.findByNameContainingIgnoreCase(query, pageable);


        return diagnosticPage.map(diagnosticMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public DiagnosticResponseDto getDiagnosisByTreatmentId(Long treatmentId) {
        Treatment treatment = treatmentRepository.findById(treatmentId).orElseThrow(() -> new TreatmentNotFoundException("El id del tratamiento no existe"));

        DiagnosticEntity diagnostic = treatment.getDiagnosis();

        return diagnosticMapper.toResponseDto(diagnostic);
    }

    @Transactional(readOnly = true)
    public Page<DiagnosticResponseDto> getDiagnosticsByConsultationId(int page, int size, Long consultationId) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid page or size parameters");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<DiagnosticEntity> diagnosticPage = diagnosticRepository.findByConsultationId(consultationId, pageable);
        return diagnosticPage.map(diagnosticMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public DiagnosticResponseDto getDiagnosisBySurgeryId(Long surgeryId) {
        Surgery surgery = surgeryRepository.findById(surgeryId).orElseThrow(() -> new SurgeryNotFoundException("La cirugiá no existe"));
        DiagnosticEntity diagnostic = surgery.getDiagnosis();
        return diagnosticMapper.toResponseDto(diagnostic);
    }

    @Transactional(readOnly = true)
    public DiagnosticResponseDto getDiagnosisByComplementaryStudyId(Long complementaryStudyId) {
        ComplementaryStudy complementaryStudy = complementaryStudyRepository.findById(complementaryStudyId).orElseThrow(() -> new ComplementaryStudyNotFoundException("El estudio complementario no existe"));
        DiagnosticEntity diagnostic = complementaryStudy.getDiagnosis();
        return diagnosticMapper.toResponseDto(diagnostic);
    }

    // Método para obtener todos los diagnósticos de una mascota por su ID
    public List<DiagnosticResponseDto> getDiagnosticsByPetId(Long petId) {
        // Verifica si la mascota existe
        if (!petRepository.existsById(petId)) {
            throw new EntityNotFoundException("Pet with id " + petId + " not found");
        }

        // Si la mascota existe, busca los diagnósticos
        List<DiagnosticEntity> diagnostics = diagnosticRepository.findAllByPetId(petId);

        if (diagnostics.isEmpty()) {
            throw new EntityNotFoundException("No diagnostics found for pet with id " + petId);
        }

        // Convertir las entidades a DTOs usando el mapper
        return diagnostics.stream()
                .map(diagnosticMapper::toResponseDto) // Usamos tu método para convertir a DTO
                .collect(Collectors.toList()); // Devuelve la lista de DTOs
    }
}
