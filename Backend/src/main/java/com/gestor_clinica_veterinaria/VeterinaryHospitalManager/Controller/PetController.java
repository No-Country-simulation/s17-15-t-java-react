package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.pet.PetRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.pet.PetResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/pet")
@Tag(name = "Pet", description = "Pet API")
public class PetController {

  private final PetService petService;

  @Autowired
  public PetController(PetService petService) {
    this.petService = petService;
  }

  @GetMapping
  @Operation(
    summary = "Get all pets",
    description = "Get all pets",
    tags = {"Pet"}
  )
  public ResponseEntity<List<PetResponse>> findAll(
      @RequestParam(required = false) String race,
      @RequestParam(required = false) String especie,
      @RequestParam(required = false) String sex,
      @RequestParam(required = false) Boolean castrated) {
    List<PetResponse> petResponseList;

    if (StringUtils.hasText(race)) {
      petResponseList = petService.getAllPetsByRace(race);
    } else if (StringUtils.hasText(especie)) {
      petResponseList = petService.getAllPetsByEspecies(especie);
    } else if (StringUtils.hasText(sex)) {
      petResponseList = petService.getAllPetsBySex(sex);
    } else if (castrated != null) {
      petResponseList = petService.getAllPetsByCastrated(castrated);
    } else {
      petResponseList = petService.getAllPets();
    }

    return ResponseEntity.ok(petResponseList);
  }

  @GetMapping("/owner/{ownerId}")
  @Operation(
    summary = "Get pets by owner id",
    description = "Get pets by owner id",
    tags = {"Pet"}
  )
  public ResponseEntity<List<PetResponse>> findAllByOwner(@PathVariable("ownerId") Long ownerId) {
    return ResponseEntity.ok(petService.getAllPetsByOwnerId(ownerId));
  }

  @PostMapping
  @Operation(
    summary = "Create pet",
    description = "Create pet",
    tags = {"Pet"}
  )
  public ResponseEntity<PetResponse> create(@RequestBody @Valid PetRequest petRequest) {
    PetResponse petCreated = petService.savePet(petRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(petCreated);
  }

  @PutMapping("/{id}")
  @Operation(
    summary = "Update pet by id",
    description = "Update pet by id",
    tags = {"Pet"}
  )
  public ResponseEntity<PetResponse> update(@PathVariable("id") Long id,
      @RequestBody @Valid PetRequest petRequest) {
    PetResponse petUpdated = petService.updatePet(id, petRequest);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(petUpdated);
  }

  @GetMapping("/{id}")
  @Operation(
    summary = "Get pet by id",
    description = "Get pet by id",
    tags = {"Pet"}
  )
  public ResponseEntity<PetResponse> getPetById(@PathVariable("id") Long id) {
    PetResponse pet = petService.getPetById(id);
    return ResponseEntity.ok(pet);
  }

  @DeleteMapping("/{id}")
  @Operation(
    summary = "Delete pet by id",
    description = "Delete pet by id",
    tags = {"Pet"}
  )
  public ResponseEntity<Map<String, String>> delete(@PathVariable("id") Long id) {
    petService.deletePet(id);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Pet eliminado correctamente");
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }
}
