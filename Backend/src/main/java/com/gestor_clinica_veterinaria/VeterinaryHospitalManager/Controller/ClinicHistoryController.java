package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ClinicHistory;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Pet;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.ClinicHistoryRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.PetRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.ClinicHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/clinic-history")
@AllArgsConstructor
public class ClinicHistoryController {

    private final ClinicHistoryService clinicHistoryService;
    private final PetRepository petRepository;
    private final ClinicHistoryRepository clinicHistoryRepository;

   // @GetMapping("/pet/{id}")
   // public ResponseEntity<ClinicHistory> getClinicHistoryByPetId(@PathVariable Long id) {
   //     ClinicHistory clinicHistory = clinicHistoryService.getClinicHistoryByIdPet(id);
//
   //     // Si el historial clínico existe, lo retornamos, sino respondemos con un 404 Not Found
   //     if (clinicHistory != null) {
   //         return ResponseEntity.ok(clinicHistory);
   //     } else {
   //         return ResponseEntity.notFound().build();
   //     }
   // }
   @GetMapping("/pet/{id}")
   public ResponseEntity<ClinicHistory> getClinicHistoryByPetId(@PathVariable Long id) {
       System.out.println("Received request for Pet ID: " + id);
       ClinicHistory clinicHistory = clinicHistoryService.getClinicHistoryByIdPet(id);

       if (clinicHistory != null) {
           return ResponseEntity.ok(clinicHistory);
       } else {
           return ResponseEntity.notFound().build();
       }
   }

    @GetMapping("/check/{id}")
    public ResponseEntity<String> checkPetAndClinicHistory(@PathVariable Long id) {
        Optional<Pet> petOptional = petRepository.findById(id);
        if (petOptional.isPresent()) {
            Optional<ClinicHistory> clinicHistoryOptional = clinicHistoryRepository.findByPet_Id(id);
            if (clinicHistoryOptional.isPresent()) {
                return ResponseEntity.ok("ClinicHistory found for Pet ID: " + id);
            } else {
                return ResponseEntity.ok("No ClinicHistory found for Pet ID: " + id);
            }
        } else {
            return ResponseEntity.ok("No Pet found with ID: " + id);
        }
    }
}
