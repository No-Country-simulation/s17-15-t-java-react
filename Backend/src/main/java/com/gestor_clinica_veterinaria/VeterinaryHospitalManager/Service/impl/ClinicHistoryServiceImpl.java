package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.impl;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ClinicHistory;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.ClinicHistoryRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.ClinicHistoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ClinicHistoryServiceImpl implements ClinicHistoryService {

    private final ClinicHistoryRepository clinicHistoryRepository;
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
}
