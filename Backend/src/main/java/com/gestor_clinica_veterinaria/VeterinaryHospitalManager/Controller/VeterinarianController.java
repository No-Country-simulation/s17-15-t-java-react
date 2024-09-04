package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.veterinarian.VeterinarianRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.veterinarian.VeterinarianResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.VeterinarianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veterinarian")
public class VeterinarianController {

    private final VeterinarianService veterinarianService;

    @Autowired
    public VeterinarianController(VeterinarianService veterinarianService) {
        this.veterinarianService = veterinarianService;
    }

    @GetMapping
    public ResponseEntity<List<VeterinarianResponse>> getAllVeterinarians() {
        List<VeterinarianResponse> veterinarians = veterinarianService.getAllVeterinarians();
        return ResponseEntity.ok(veterinarians);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarianResponse> getVeterinarianById(@PathVariable("id") Long veterinarianId) {
        VeterinarianResponse veterinarian = veterinarianService.getVeterinarianById(veterinarianId);
        return ResponseEntity.ok(veterinarian);
    }

    @GetMapping("/specialty/{specialty}")
    public ResponseEntity<List<VeterinarianResponse>> getVeterinarianBySpecialty(@PathVariable("specialty") String specialty) {
        List<VeterinarianResponse> veterinarians = veterinarianService.getVeterinarianBySpecialty(specialty);
        return ResponseEntity.ok(veterinarians);
    }

    @PostMapping
    public ResponseEntity<VeterinarianResponse> addVeterinarian(@RequestBody @Valid VeterinarianRequest veterinarianRequest) {
        VeterinarianResponse newVeterinarian = veterinarianService.addVeterinarian(veterinarianRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVeterinarian);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarianResponse> updateVeterinarian(@PathVariable("id") Long veterinarianId, @RequestBody @Valid VeterinarianRequest veterinarianRequest) {
        VeterinarianResponse updatedVeterinarian = veterinarianService.updateVeterinarianById(veterinarianId, veterinarianRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedVeterinarian);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeterinarian(@PathVariable("id") Long veterinarianId) {
        veterinarianService.deleteVeterinarianById(veterinarianId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
