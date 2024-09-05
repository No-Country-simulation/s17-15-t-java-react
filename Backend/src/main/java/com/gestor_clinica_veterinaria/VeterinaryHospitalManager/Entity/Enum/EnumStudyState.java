package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum;

public enum EnumStudyState {
    PENDIENTE, //study is scheduled but has not been performed yet
    EN_PROGRESO, // The study is currently being conducted
    COMPLETADO, //The study has been completed.
    ESPERANDO_RESULTADOS, // The study has been completed, but the results are not yet available.
    RESULTADOS_LISTOS, // The results of the study are ready for review
    REVISADOS, // The results have been reviewed by the veterinarian.
    CANCELADOS, //The study was scheduled but then cancelled.
    FALLIDOS, // The study could not be completed due to technical or other issues.
}
