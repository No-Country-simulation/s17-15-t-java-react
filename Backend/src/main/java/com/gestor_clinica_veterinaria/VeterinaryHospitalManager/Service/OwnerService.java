package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner.OwnerRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner.OwnerResponse;
import java.util.List;

public interface OwnerService {

  List<OwnerResponse> findAllOwner();

  List<OwnerResponse> finAllOwnerByName(String name);

  OwnerResponse OwnerById(Long id);

  OwnerResponse saveOwner(OwnerRequest ownerRequest);

  OwnerResponse updateOwner(Long id, OwnerRequest ownerRequest);

  void deleteOwner(Long id);
}
