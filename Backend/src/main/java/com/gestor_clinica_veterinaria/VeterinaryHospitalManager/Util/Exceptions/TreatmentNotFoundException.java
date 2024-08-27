package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Util.Exceptions;

public class TreatmentNotFoundException extends RuntimeException {

    public TreatmentNotFoundException(String message){
        super(message);
    }
}
