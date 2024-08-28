package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Mapper;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.RequestCreateSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.RequestEditSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.ResponseSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Surgery;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface SurgeryMapper {

    ResponseSurgery toResponseDTO(Surgery surgery);

   // @Mapping(source = "consultationId", target = "consultation.id")
    Surgery toEntity(RequestCreateSurgery dto);

   /*
   @Mapping(target = "consultation.id", source = "consultationId")
    Surgery requestCreateToSurgery(RequestCreateSurgery requestCreateSurgery);

    Surgery requestEditToSurgery(RequestEditSurgery requestEditSurgery);

    ResponseSurgery surgeryToResponse(Surgery surgery);

    List<ResponseSurgery> surgeryToResponseList(List<Surgery> surgeries);
   Surgery requestCreateToSurgery (RequestCreateSurgery requestCreateSurgery);
   Surgery requestEditToSurgery (RequestEditSurgery requestEditSurgery);
   ResponseSurgery surgeryToResponse (Surgery surgery);
   //Surgery requestCreateToSurgery (RequestCreateSurgery requestCreateSurgery);
  */

}
