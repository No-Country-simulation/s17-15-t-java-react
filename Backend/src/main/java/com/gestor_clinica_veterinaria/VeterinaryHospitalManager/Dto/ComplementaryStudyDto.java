package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.DiagnosticEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study.EnumStudyState;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record ComplementaryStudyDto (
        @NotBlank(message = "El estudio debe contener una fecha correspondiente al día que se ejecuta el mismo.")
        LocalDate examinationDate,

        @NotBlank(message = "El estudio debe tener un tipo.")
        String studyType,

        @NotBlank(message = "El estudio debe tener un resultado.")
        String studyResult,

        @NotBlank(message = "El estudio debe tener un estado.")
        EnumStudyState studyState,
        byte[] studyFile,
        @NotBlank(message = "El estudio debe tener un costo.")
        Double studyCost

//        DiagnosticEntity dianosis //corregir cuando se corrija el nombre a ingles

        //@NotBlank(message = "El estudio debe estar relacionado a una consulta.")
        //Consultation consultation,

        //Hospitalization hospitalization
    ){
}
