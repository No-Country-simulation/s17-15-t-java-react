package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.veterinarian.VeterinarianRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.veterinarian.VeterinarianResponse;

import java.util.List;

public interface VeterinarianService {

    List<VeterinarianResponse> getAllVeterinarians();

    VeterinarianResponse getVeterinarianById(Long veterinarianId);

    List<VeterinarianResponse> getVeterinarianBySpecialty(String specialty);

    VeterinarianResponse addVeterinarian(VeterinarianRequest veterinarianRequest);

    VeterinarianResponse updateVeterinarianById(Long veterinarianId, VeterinarianRequest veterinarianRequest);

    void deleteVeterinarianById(Long veterinarianId);

}
