package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.impl;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.veterinarian.VeterinarianRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.veterinarian.VeterinarianResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Veterinarian;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Exceptions.ResourceNotFoundException;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.VeterinarianMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.VeterinarianRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.VeterinarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VeterinarianServiceImpl implements VeterinarianService {

    private final VeterinarianRepository veterinarianRepository;

    @Autowired
    public VeterinarianServiceImpl(VeterinarianRepository veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<VeterinarianResponse> getAllVeterinarians() {
        return VeterinarianMapper.toListDto(veterinarianRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public VeterinarianResponse getVeterinarianById(Long veterinarianId) {
        Veterinarian veterinarian = veterinarianRepository.findById(veterinarianId)
                .orElseThrow(() -> new ResourceNotFoundException("El veterinario con el id " + veterinarianId + " no existe"));
        return VeterinarianMapper.toDto(veterinarian);
    }

    @Transactional(readOnly = true)
    @Override
    public List<VeterinarianResponse> getVeterinarianBySpecialty(String specialty) {
        return VeterinarianMapper.toListDto(veterinarianRepository.findAllBySpecialtyIgnoreCase(specialty));
    }

    @Override
    public VeterinarianResponse addVeterinarian(VeterinarianRequest veterinarianRequest) {
        Veterinarian veterinarian = VeterinarianMapper.toEntity(veterinarianRequest);
        Veterinarian newVeterinarian = veterinarianRepository.save(veterinarian);
        return VeterinarianMapper.toDto(newVeterinarian);
    }

    @Override
    public VeterinarianResponse updateVeterinarianById(Long veterinarianId, VeterinarianRequest veterinarianRequest) {
        Veterinarian oldVeterinarian = veterinarianRepository.findById(veterinarianId)
                .orElseThrow(() -> new ResourceNotFoundException("El veterinario con el id " + veterinarianId + " no existe"));
        VeterinarianMapper.updateEntity(oldVeterinarian, veterinarianRequest);
        Veterinarian updatedVeterinarian = veterinarianRepository.save(oldVeterinarian);
        return VeterinarianMapper.toDto(updatedVeterinarian);
    }

    @Override
    public void deleteVeterinarianById(Long veterinarianId) {
        if (!veterinarianRepository.existsById(veterinarianId)) {
            throw new ResourceNotFoundException("El veterinario con el id " + veterinarianId + " no existe");
        }
        veterinarianRepository.deleteById(veterinarianId);
    }

}
