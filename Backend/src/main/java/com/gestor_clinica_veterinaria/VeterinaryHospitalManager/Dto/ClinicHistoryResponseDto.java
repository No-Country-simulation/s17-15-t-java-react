package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Consultation.ConsultationDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Consultation.ConsultationResponseDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Diagnosis.DiagnosticDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.Diagnosis.DiagnosticResponseDto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.surgery.ResponseSurgery;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.treatment.TreatmentCreationResponse;

import java.util.List;

public record ClinicHistoryResponseDto(


        Long idClinicHistory,
        List<ConsultationResponseDto> consultations,
        List<DiagnosticResponseDto> diagnoses,
        List<TreatmentCreationResponse> treatments,
        List<ResponseSurgery> surgeries
       // List<> complementaryStudies,
      //  List<HospitalizationDto> hospitalizations
) {


}
