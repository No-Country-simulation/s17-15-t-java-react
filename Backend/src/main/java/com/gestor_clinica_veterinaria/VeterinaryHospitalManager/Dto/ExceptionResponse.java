package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public record ExceptionResponse(
    @JsonProperty("status_code")
    int statusCode,

    @JsonProperty("http_method")
    String httpMethod,

    String message,

    @JsonProperty("backend_message")
    String backendMessage,

    LocalDateTime timestamp,

    List<String> details
) {

}
