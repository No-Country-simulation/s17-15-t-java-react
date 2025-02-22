package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.impl;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.RequestCreateSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.RequestEditSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.ResponseSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Surgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Exceptions.ResourceNotFoundException;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.SurgeryMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.DiagnosticRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.PetRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.SurgeryRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.VeterinarianRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.SurgeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurgeryServiceImpl implements SurgeryService {

    private final SurgeryRepository surgeryRepository;
    private final SurgeryMapper surgeryMapper;
    private final DiagnosticRepository diagnosticRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final PetRepository petRepository;

    @Override
    public ResponseSurgery addSurgery(RequestCreateSurgery requestCreateSurgery) {
        // Mapeo manual
        Surgery surgery = new Surgery();
        surgery.setDiagnosis(diagnosticRepository.findById(requestCreateSurgery.diagnosticId())
                .orElseThrow(() -> new ResourceNotFoundException("El diagnostico solicitado no existe")));
        surgery.setVeterinarian(veterinarianRepository.findById(requestCreateSurgery.veterinarianId())
                .orElseThrow(() -> new ResourceNotFoundException("El veterinario solicitado no existe")));
        surgery.setDateSurgery(requestCreateSurgery.dateSurgery());
        surgery.setSurgeryType(requestCreateSurgery.surgeryType());
        surgery.setSurgeryProcedure(requestCreateSurgery.surgeryProcedure());
        surgery.setObservations(requestCreateSurgery.observations());
        surgery.setPostSurgeryRecommendations(requestCreateSurgery.postSurgeryRecommendations());
        surgery.setSurgeryCost(requestCreateSurgery.surgeryCost());

        // Guardar en la base de datos
        Surgery savedSurgery = surgeryRepository.save(surgery);

        savedSurgery.getDiagnosis().getSurgerys().add(savedSurgery);
        //savedSurgery.getVeterinarian().getSurgeries().add(savedSurgery);


        // Mapeo manual de respuesta
        return new ResponseSurgery(
                savedSurgery.getId(),
                savedSurgery.getDateSurgery(),
                savedSurgery.getSurgeryType(),
                savedSurgery.getSurgeryProcedure(),
                savedSurgery.getObservations(),
                savedSurgery.getPostSurgeryRecommendations(),
                savedSurgery.getSurgeryCost()
        );
    }

    @Transactional
    @Override
    public List<ResponseSurgery> getAllSurgeries(String surgeryName) {
        List<Surgery> surgeries;

        if (surgeryName != null && !surgeryName.isEmpty()) {
            surgeries = surgeryRepository.findBySurgeryTypeContainingIgnoreCase(surgeryName);
        } else {
            surgeries = surgeryRepository.findAll();
        }

        return surgeries.stream().map(surgery -> new ResponseSurgery(
                surgery.getId(),
                surgery.getDateSurgery(),
                surgery.getSurgeryType(),
                surgery.getSurgeryProcedure(),
                surgery.getObservations(),
                surgery.getPostSurgeryRecommendations(),
                surgery.getSurgeryCost()
        )).collect(Collectors.toList());
    }
    @Override
    public ResponseSurgery getSurgeryById(Long surgeryId) {
        Surgery surgery = surgeryRepository.findById(surgeryId)
                .orElseThrow(() -> new ResourceNotFoundException("La cirugía solicitada no existe"));

        return new ResponseSurgery(
                surgery.getId(),
                surgery.getDateSurgery(),
                surgery.getSurgeryType(),
                surgery.getSurgeryProcedure(),
                surgery.getObservations(),
                surgery.getPostSurgeryRecommendations(),
                surgery.getSurgeryCost()
        );
    }

    @Override
    public ResponseSurgery updateSurgery(Long surgeryId, RequestEditSurgery requestEditSurgery) {
        Surgery surgery = surgeryRepository.findById(surgeryId)
                .orElseThrow(() -> new ResourceNotFoundException("La cirugía no se puede actualizar porque no existe"));

        surgery.setDateSurgery(requestEditSurgery.dateSurgery());
        surgery.setSurgeryType(requestEditSurgery.surgeryType());
        surgery.setSurgeryProcedure(requestEditSurgery.surgeryProcedure());
        surgery.setObservations(requestEditSurgery.observations());
        surgery.setPostSurgeryRecommendations(requestEditSurgery.postSurgeryRecommendations());
        surgery.setSurgeryCost(requestEditSurgery.surgeryCost());

        Surgery updatedSurgery = surgeryRepository.save(surgery);

        return new ResponseSurgery(
                updatedSurgery.getId(),
                updatedSurgery.getDateSurgery(),
                updatedSurgery.getSurgeryType(),
                updatedSurgery.getSurgeryProcedure(),
                updatedSurgery.getObservations(),
                updatedSurgery.getPostSurgeryRecommendations(),
                updatedSurgery.getSurgeryCost()
        );
    }

    @Override
    public void deleteSurgery(Long surgeryId) {
        if (!surgeryRepository.existsById(surgeryId)) {
            throw new ResourceNotFoundException("La cirugía no se puede eliminar porque no existe");
        }
        surgeryRepository.deleteById(surgeryId);
    }


    @Transactional(readOnly = true)
    @Override
    public List<ResponseSurgery> getAllSurgeriesByPetId(Long petId) {
        // Verifica si la mascota existe
        if (!petRepository.existsById(petId)) {
            throw new ResourceNotFoundException("La mascota con id " + petId + " no existe");
        }

        // Obtener las cirugías asociadas al petId
        List<Surgery> surgeries = surgeryRepository.findSurgeriesByPetId(petId);

        // Mapeo manual de las cirugías a ResponseSurgery
        return surgeries.stream().map(surgery -> new ResponseSurgery(
                surgery.getId(),
                surgery.getDateSurgery(),
                surgery.getSurgeryType(),
                surgery.getSurgeryProcedure(),
                surgery.getObservations(),
                surgery.getPostSurgeryRecommendations(),
                surgery.getSurgeryCost()
        )).collect(Collectors.toList());
    }



}
