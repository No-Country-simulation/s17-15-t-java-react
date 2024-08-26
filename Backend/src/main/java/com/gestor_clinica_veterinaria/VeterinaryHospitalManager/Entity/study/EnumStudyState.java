package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.study;

public enum EnumStudyState {
    PENDING, //study is scheduled but has not been performed yet
    IN_PROGRESS, // The study is currently being conducted
    COMPLETED, //The study has been completed.
    AWAITING_RESULTS, // The study has been completed, but the results are not yet available.
    RESULTS_READY, // The results of the study are ready for review
    REVIEWED, // The results have been reviewed by the veterinarian.
    CANCELLED, //The study was scheduled but then cancelled.
    FAILED, // The study could not be completed due to technical or other issues.
}
