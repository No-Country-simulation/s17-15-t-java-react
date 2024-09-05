package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.veterinarian.VeterinarianRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.veterinarian.VeterinarianResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Veterinarian;

import java.util.List;

public class VeterinarianMapper {

    public static VeterinarianResponse toDto(Veterinarian veterinarian) {
        if (veterinarian == null) {
            return null;
        }

        return new VeterinarianResponse(
                veterinarian.getId(),
                veterinarian.getName(),
                veterinarian.getLastName(),
                veterinarian.getProfessionalLicenceNumber(),
                veterinarian.getSpecialty()
//                veterinarian.
        );
    }

    public static List<VeterinarianResponse> toListDto(List<Veterinarian> veterinarians) {
        if (veterinarians == null) {
            return null;
        }

        return veterinarians.stream().map(VeterinarianMapper::toDto).toList();
    }

    public static Veterinarian toEntity(VeterinarianRequest veterinarianRequest) {
        if (veterinarianRequest == null) {
            return null;
        }

        Veterinarian veterinarian = new Veterinarian();
        veterinarian.setName(veterinarianRequest.name());
        veterinarian.setLastName(veterinarianRequest.lastname());
        veterinarian.setProfessionalLicenceNumber(veterinarianRequest.professionalLicenceNumber());
        veterinarian.setSpecialty(veterinarianRequest.specialty());

        return veterinarian;
    }

    public static void updateEntity(Veterinarian oldVeterinarian, VeterinarianRequest veterinarianRequest) {
        if (oldVeterinarian == null || veterinarianRequest == null) {
            return;
        }

        if (veterinarianRequest.name() != null){

            oldVeterinarian.setName(veterinarianRequest.name());

        }
        if (veterinarianRequest.lastname() != null){

            oldVeterinarian.setLastName(veterinarianRequest.lastname());

        }
        if (veterinarianRequest.professionalLicenceNumber() != null){

            oldVeterinarian.setProfessionalLicenceNumber(veterinarianRequest.professionalLicenceNumber());

        }
        if (veterinarianRequest.specialty() != null){

            oldVeterinarian.setSpecialty(veterinarianRequest.specialty());

        }

    }

}
