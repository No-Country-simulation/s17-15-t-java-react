package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Exceptions;

public class DiagnosticNotFoundException extends RuntimeException {
    public DiagnosticNotFoundException(String elDiagnosticoBuscadoNoExiste) {
        super(elDiagnosticoBuscadoNoExiste);
    }
}
