package com.gestor_clinica_veterinaria.VeterinaryHospitalManager;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Enum.EnumRole;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.PermissionEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.RoleEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.UserEntity;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.PermissionRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.RoleRepository;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class VeterinaryHospitalManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeterinaryHospitalManagerApplication.class, args);
	}

}
