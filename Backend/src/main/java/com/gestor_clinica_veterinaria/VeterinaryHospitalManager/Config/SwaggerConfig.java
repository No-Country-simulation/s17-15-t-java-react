package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@OpenAPIDefinition(
        info = @Info(
                title = "Veterinary Hospital Manager API",
                description = "API para la gestión de la clinica veterinaria",
                termsOfService = "www.clinicaveterinaria.com/terms/",
                version = "1.0.0",
                contact = @Contact(
                        name = "Gestor Clinica",
                        url = "www.clinicaveterinaria.com/contact/",
                        email = "clinicaveterinaria@gmail.com"
                ),
                license = @License(
                        name = "Standard Apache License Version 2.0 for Clínica Veterinaria",
                        url = "www.clinicaveterinaria.com/license/",
                        identifier = "Apache-2.0"
                )
        ),
        servers = {
                @Server(
                        description = "Local Server",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Heroku Server",
                        url = "http://89.0.142.86:8080"
                )
        }
        /*security = @SecurityRequirement(
                name = "securityToken"
        )*/
)
@SecurityScheme(
        name = "securityToken",
        description = "Access Token For My API",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
