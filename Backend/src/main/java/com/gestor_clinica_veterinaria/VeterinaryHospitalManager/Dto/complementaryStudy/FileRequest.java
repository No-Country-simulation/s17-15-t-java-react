package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy;

public record FileRequest (
        String fileName,
        String filePath,
        String fileType,
        Long fileSize
){
}
