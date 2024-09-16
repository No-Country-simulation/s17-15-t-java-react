package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.RequestCreateSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.RequestEditSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.ResponseSurgery;

import java.util.List;

public interface SurgeryService {

    ResponseSurgery addSurgery(RequestCreateSurgery requestCreateSurgery);
    List<ResponseSurgery> getAllSurgeries(String surgeryName);
    ResponseSurgery getSurgeryById(Long surgeryId);
   // List<ResponseSurgery> getSurgeriesByOwnerId(Long ownerId);
   // List<ResponseSurgery> getSurgeriesByPetId(Long petId);

    ResponseSurgery updateSurgery(Long surgeryId, RequestEditSurgery requestEditSurgery);
    void deleteSurgery(Long surgeryId);

    List<ResponseSurgery> getAllSurgeriesByPetId(Long petId);
}
