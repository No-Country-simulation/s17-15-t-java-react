package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Service;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Exceptions.FileNotFoundException;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileDownloadService {
    private final String bucketName = "studyfiles_vetcare";
    private final Storage storage = StorageOptions.getDefaultInstance().getService();

    public byte[] downloadFileFromUrl(String url) {

        BlobId blobId = BlobId.fromGsUtilUri(url);
        Blob blob = storage.get(blobId);

        if (blob != null) {
            return blob.getContent();

        } else {
            throw new FileNotFoundException("File not found: " + url);
        }
    }
}
