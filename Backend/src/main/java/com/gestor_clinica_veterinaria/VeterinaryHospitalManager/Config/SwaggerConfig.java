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
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mapstruct.ap.shaded.freemarker.ext.dom.XPathSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;

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
                        description = "Render Server",
                        url = "https://veterinaria-bef3.onrender.com"
                )
        },
        security = @SecurityRequirement(
                name = "securityToken"
        )
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
