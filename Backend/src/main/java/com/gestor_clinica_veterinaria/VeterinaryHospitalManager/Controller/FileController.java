package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Controller;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto.complementaryStudy.StudyResponse;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.ComplementaryStudy;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service.ComplementaryStudyService;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Exceptions.FileNotFoundException;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class FileController {

    private final ComplementaryStudyService complementaryStudyService;
    private final Storage storage = StorageOptions.getDefaultInstance().getService();

    @GetMapping("/download/{studyId}")
    public byte[] downloadFileFromUrl(String studyId) {
        String url = getStudyUrl(studyId);
        BlobId blobId = BlobId.fromGsUtilUri(url);
        Blob blob = storage.get(blobId);
        if (blob != null) {
            return blob.getContent();
        } else {
            throw new FileNotFoundException("File not found: " + url);
        }
    }

    private String getStudyUrl(String studyId) {
        Long studyIdLong = Long.parseLong(studyId);
        StudyResponse study = complementaryStudyService.getStudyById(studyIdLong);
        return study.studyFile();
    }

}