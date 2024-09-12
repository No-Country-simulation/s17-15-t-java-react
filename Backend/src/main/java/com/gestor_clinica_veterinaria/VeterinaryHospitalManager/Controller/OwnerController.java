package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner.OwnerRequest;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.owner.OwnerResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/owner")
@Tag(name = "Owner Management", description = "APIs para gestionar propietarios")
public class OwnerController {

  private final OwnerService ownerService;

  @Autowired
  public OwnerController(OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @Operation(
          summary = "Obtener todos los propietarios",
          description = "Devuelve una lista de todos los propietarios registrados en el sistema. Se puede filtrar por nombre o apellido."
  )
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Propietarios obtenidos con éxito"),
          @ApiResponse(responseCode = "404", description = "No se encontraron propietarios")
  })
  @GetMapping
  public ResponseEntity<List<OwnerResponse>> getAll(
          @Parameter(description = "Nombre opcional para filtrar propietarios por nombre")
          @RequestParam(required = false) String name,
          @Parameter(description = "Apellido opcional para filtrar propietarios por apellido")
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

  @Operation(
          summary = "Crear un nuevo propietario",
          description = "Registra un nuevo propietario en el sistema con los detalles proporcionados."
  )
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Propietario creado con éxito"),
          @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos")
  })
  @PostMapping
  public ResponseEntity<OwnerResponse> create(
          @Parameter(description = "Detalles del propietario que se va a crear", required = true)
          @RequestBody @Valid OwnerRequest ownerRequest) {
    OwnerResponse ownerCreated = ownerService.saveOwner(ownerRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(ownerCreated);
  }

  @Operation(
          summary = "Obtener un propietario por ID",
          description = "Devuelve los detalles de un propietario específico usando su ID."
  )
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Propietario obtenido con éxito"),
          @ApiResponse(responseCode = "404", description = "No se encontró un propietario con el ID proporcionado")
  })
  @GetMapping("/{id}")
  public ResponseEntity<OwnerResponse> getById(
          @Parameter(description = "ID del propietario a buscar", required = true)
          @PathVariable("id") Long id) {
    return ResponseEntity.ok(ownerService.OwnerById(id));
  }

  @Operation(
          summary = "Actualizar un propietario existente",
          description = "Actualiza los detalles de un propietario específico usando su ID."
  )
  @ApiResponses(value = {
          @ApiResponse(responseCode = "202", description = "Propietario actualizado con éxito"),
          @ApiResponse(responseCode = "404", description = "No se encontró un propietario con el ID proporcionado"),
          @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos")
  })
  @PutMapping("/{id}")
  public ResponseEntity<OwnerResponse> update(
          @Parameter(description = "ID del propietario que se va a actualizar", required = true)
          @PathVariable("id") Long id,
          @Parameter(description = "Detalles actualizados del propietario", required = true)
          @RequestBody @Valid OwnerRequest ownerRequest) {
    OwnerResponse updatedOwner = ownerService.updateOwner(id, ownerRequest);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedOwner);
  }

  @Operation(
          summary = "Eliminar un propietario",
          description = "Elimina un propietario del sistema utilizando su ID."
  )
  @ApiResponses(value = {
          @ApiResponse(responseCode = "202", description = "Propietario eliminado con éxito"),
          @ApiResponse(responseCode = "404", description = "No se encontró un propietario con el ID proporcionado")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, String>> delete(
          @Parameter(description = "ID del propietario que se va a eliminar", required = true)
          @PathVariable("id") Long id) {
    ownerService.deleteOwner(id);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Owner eliminado Correctamente");
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }
}
