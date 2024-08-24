package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner.OwnerRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner.OwnerResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
public class OwnerController {

  private final OwnerService ownerService;

  @Autowired
  public OwnerController(OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @PostMapping
  public ResponseEntity<OwnerResponse> create(@RequestBody @Valid OwnerRequest ownerRequest) {
    OwnerResponse ownerCreated = ownerService.saveOwner(ownerRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(ownerCreated);
  }
}
