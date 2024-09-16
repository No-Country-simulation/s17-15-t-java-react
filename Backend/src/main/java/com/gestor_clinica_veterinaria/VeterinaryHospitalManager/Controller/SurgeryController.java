package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.RequestCreateSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.RequestEditSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.ResponseSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Surgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper.SurgeryMapper;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.SurgeryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sugery")
@Tag(name = "Surgery Management", description = "APIs para gestionar cirugías")
public class SurgeryController {

    public final SurgeryService surgeryService;
    public final SurgeryMapper surgeryMapper;


    @Operation(
            summary = "Obtener detalles de una cirugía por ID",
            description = "Devuelve los detalles de una cirugía específica en base a su ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalles de la cirugía encontrados con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontró una cirugía con el ID proporcionado")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseSurgery> getSurgeryById(
            @Parameter(description = "ID de la cirugía a buscar", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(surgeryService.getSurgeryById(id));
    }

    @Operation(
            summary = "Obtener todas las cirugías",
            description = "Devuelve una lista de todas las cirugías registradas en el sistema. Si se especifica el nombre de la cirugía, devuelve una lista filtrada por ese nombre."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cirugías obtenida con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontraron cirugías")
    })
    @GetMapping
    public ResponseEntity<List<ResponseSurgery>> getAllSurgeries(
            @Parameter(description = "Nombre opcional de la cirugía para filtrar los resultados")
            @RequestParam(required = false) String surgeryName) {
        List<ResponseSurgery> surgeryList = surgeryService.getAllSurgeries(surgeryName);
        return ResponseEntity.ok(surgeryList);
    }

    @Operation(
            summary = "Registrar una nueva cirugía",
            description = "Crea una nueva cirugía en el sistema con los detalles proporcionados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cirugía creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud, los datos proporcionados no son válidos")
    })
    @PostMapping("/add")
    public ResponseEntity<ResponseSurgery> addSurgery(
            @Parameter(description = "Detalles de la cirugía que se va a crear", required = true)
            @RequestBody @Valid RequestCreateSurgery requestCreateSurgery) {
        return ResponseEntity.status(HttpStatus.CREATED).body(surgeryService.addSurgery(requestCreateSurgery));
    }

    @Operation(
            summary = "Actualizar una cirugía existente",
            description = "Actualiza los detalles de una cirugía específica utilizando el ID de la cirugía."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cirugía actualizada con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontró una cirugía con el ID proporcionado"),
            @ApiResponse(responseCode = "400", description = "Error en los datos de actualización proporcionados")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseSurgery> updateSurgery(
            @Parameter(description = "ID de la cirugía que se va a actualizar", required = true)
            @PathVariable Long id,
            @Parameter(description = "Detalles actualizados de la cirugía", required = true)
            @RequestBody @Valid RequestEditSurgery requestEditSurgery) {
        return ResponseEntity.ok(surgeryService.updateSurgery(id, requestEditSurgery));
    }

    @Operation(
            summary = "Eliminar una cirugía",
            description = "Elimina una cirugía del sistema utilizando su ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cirugía eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontró una cirugía con el ID proporcionado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSurgery(
            @Parameter(description = "ID de la cirugía que se va a eliminar", required = true)
            @PathVariable Long id) {
        surgeryService.deleteSurgery(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Obtener todas las cirugías de un propietario",
            description = "Devuelve una lista de cirugías asociadas al propietario con el ID especificado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cirugías del propietario obtenidas con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontraron cirugías para el propietario con el ID proporcionado")
    })
    @GetMapping("/owner/{ownerId}")
    public List<Surgery> getSurgeryByOwnerId(
            @Parameter(description = "ID del propietario cuyas cirugías se desean consultar", required = true)
            @PathVariable Long ownerId) {
        // Retorna una lista vacía para propósitos de visualización en Swagger
        return Collections.emptyList();
    }

    @Operation(
            summary = "Obtener todas las cirugías de una mascota",
            description = "Devuelve una lista de cirugías asociadas a la mascota con el ID especificado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cirugías de la mascota obtenidas con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontraron cirugías para la mascota con el ID proporcionado")
    })
   // @GetMapping("/pet/{petId}")
   // public List<Surgery> getSurgeriesByPetId(
   //         @Parameter(description = "ID de la mascota cuyas cirugías se desean consultar", required = true)
   //         @PathVariable Long petId) {
   //     // Retorna una lista vacía para propósitos de visualización en Swagger
   //     return Collections.emptyList();
   // }
  //  @GetMapping("/owner/{ownerId}")
  //  public List<Surgery> getSurgeryByOwnerId(@PathVariable Long ownerId) {
  //      return surgeryService.getSurgeryByOwnerId(ownerId);
  //  }
//
  //  @GetMapping("/pet/{petId}")
  //  public List<Surgery> getSurgeriesByPetId(@PathVariable Long petId) {
  //      return surgeryService.getSurgeriesByPetId(petId);
  //  }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<ResponseSurgery>> getSurgeriesByPetId(@PathVariable Long petId) {
        List<ResponseSurgery> surgeries = surgeryService.getAllSurgeriesByPetId(petId);
        if (surgeries.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 si no hay cirugías
        }
        return ResponseEntity.ok(surgeries); // Devuelve la lista de cirugías
    }

}
