package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileStorageService {
    private final String bucketName = "studyfiles_vetcare";
    private final Storage storage = StorageOptions.getDefaultInstance().getService();

    public String saveFile(MultipartFile file) {

        //String objectName = file.getOriginalFilename();
        String objectName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        try {
            BlobId blobId = BlobId.of(bucketName, objectName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

            byte[] fileBytes = file.getBytes();
            storage.create(blobInfo, fileBytes);

            return "gs://" + bucketName + "/" + objectName;

        } catch (IOException e) {
            throw new RuntimeException("Error al subir el archivo a Google Cloud Storage: " + e.getMessage());
        }
    }
}
