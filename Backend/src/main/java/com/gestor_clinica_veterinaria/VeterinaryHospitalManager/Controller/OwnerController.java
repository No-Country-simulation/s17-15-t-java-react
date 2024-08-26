package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner.OwnerRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner.OwnerResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.OwnerService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
public class OwnerController {

  private final OwnerService ownerService;

  @Autowired
  public OwnerController(OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @GetMapping
  public ResponseEntity<List<OwnerResponse>> getAll(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String lastname) {

    List<OwnerResponse> ownerResponseList;

    if (StringUtils.hasText(name)) {
      ownerResponseList = ownerService.finAllOwnerByName(name);
    } else if (StringUtils.hasText(lastname)) {
      ownerResponseList = ownerService.findAllOwnerByLastname(lastname);
    } else {
      ownerResponseList = ownerService.findAllOwner();
    }

    return ResponseEntity.ok(ownerResponseList);
  }


  @PostMapping
  public ResponseEntity<OwnerResponse> create(@RequestBody @Valid OwnerRequest ownerRequest) {
    OwnerResponse ownerCreated = ownerService.saveOwner(ownerRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(ownerCreated);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OwnerResponse> getById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(ownerService.OwnerById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<OwnerResponse> update(@PathVariable("id") Long id,
      @RequestBody @Valid OwnerRequest ownerRequest) {
    OwnerResponse updatedOwner = ownerService.updateOwner(id, ownerRequest);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedOwner);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, String>> delete(@PathVariable("id") Long id) {
    ownerService.deleteOwner(id);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Owner eliminado Correctamente");
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }
}
