package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.RequestCreateSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.RequestEditSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.ResponseSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.SurgeryMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.SurgeryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sugery")
public class SurgeryController {

    public final SurgeryService surgeryService;
    public final SurgeryMapper surgeryMapper;


    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseSurgery> getSurgeryById(@PathVariable Long id) {
        return ResponseEntity.ok(surgeryService.getSurgeryById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseSurgery>> getAllSurgeries() {
        return ResponseEntity.ok(surgeryService.getAllSurgeries());
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseSurgery> addSurgery(@RequestBody @Valid RequestCreateSurgery requestCreateSurgery) {
        return ResponseEntity.status(HttpStatus.CREATED).body(surgeryService.addSurgery(requestCreateSurgery));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseSurgery> updateSurgery(@PathVariable Long id, @RequestBody @Valid RequestEditSurgery requestEditSurgery) {
        return ResponseEntity.ok(surgeryService.updateSurgery(id, requestEditSurgery));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSurgery(@PathVariable Long id) {
        surgeryService.deleteSurgery(id);
        return ResponseEntity.noContent().build();
    }
}
