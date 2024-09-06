package com.gestor_clinica_veterinaria.VeterinaryHospitalManager;

import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Entity.Veterinarian;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Repository.VeterinarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class VeterinaryHospitalManagerApplication implements CommandLineRunner {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private VeterinarianRepository veterinarianRepository;


	public static void main(String[] args) {
		SpringApplication.run(VeterinaryHospitalManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		jdbcTemplate.update("INSERT INTO permissions (id, name) VALUES (?, ?) ON CONFLICT (id) DO NOTHING", 1, "CREATE");
		jdbcTemplate.update("INSERT INTO permissions (id, name) VALUES (?, ?) ON CONFLICT (id) DO NOTHING", 2, "READ");
		jdbcTemplate.update("INSERT INTO permissions (id, name) VALUES (?, ?) ON CONFLICT (id) DO NOTHING", 3, "WRITE");
		jdbcTemplate.update("INSERT INTO permissions (id, name) VALUES (?, ?) ON CONFLICT (id) DO NOTHING", 4, "DELETE");

		// Insertar roles
		jdbcTemplate.update("INSERT INTO roles (id, role_name) VALUES (?, ?) ON CONFLICT (id) DO NOTHING", 1, "ADMIN");
		jdbcTemplate.update("INSERT INTO roles (id, role_name) VALUES (?, ?) ON CONFLICT (id) DO NOTHING", 2, "USER");

		// Relacionar roles con permisos
		jdbcTemplate.update("INSERT INTO role_permission (role_id, permission_id) VALUES (?, ?) ON CONFLICT DO NOTHING", 1, 1);
		jdbcTemplate.update("INSERT INTO role_permission (role_id, permission_id) VALUES (?, ?) ON CONFLICT DO NOTHING", 1, 2);
		jdbcTemplate.update("INSERT INTO role_permission (role_id, permission_id) VALUES (?, ?) ON CONFLICT DO NOTHING", 1, 3);
		jdbcTemplate.update("INSERT INTO role_permission (role_id, permission_id) VALUES (?, ?) ON CONFLICT DO NOTHING", 1, 4);
		jdbcTemplate.update("INSERT INTO role_permission (role_id, permission_id) VALUES (?, ?) ON CONFLICT DO NOTHING", 2, 2);

		// Insertar usuario administrador
		/*jdbcTemplate.update("INSERT INTO users (id, username, password, email, is_enabled, account_no_expired, account_no_locked, credentials_no_expired) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT (id) DO NOTHING",
				100000, "admin", "$2a$10$Dxnp0wJeF8L9Ftd8./ExcuaJ62jKKVezB3gvBLcgo1qNOjAnR6QJi", "admin@example.com", true, true, true, true);*/

		Veterinarian veterinarian = new Veterinarian();
		veterinarian.setUsername("admin");
		veterinarian.setPassword("$2a$10$Dxnp0wJeF8L9Ftd8./ExcuaJ62jKKVezB3gvBLcgo1qNOjAnR6QJi");
		veterinarian.setEmail("admin@example.com");
		veterinarian.setEnabled(true);
		veterinarian.setAccountNoExpired(true);
		veterinarian.setAccountNoLocked(true);
		veterinarian.setCredentialsNoExpired(true);
		veterinarian.setLastName("admin");
		veterinarian.setSpecialty("admin");
		veterinarian.setProfessionalLicenceNumber("12345");

		Veterinarian savedVeterinarian = veterinarianRepository.save(veterinarian);
		Long veterinarianId = savedVeterinarian.getId();
		// Relacionar el usuario administrador con el rol ADMIN
		jdbcTemplate.update("INSERT INTO users_roles (user_id, role_id) VALUES (?, ?) ON CONFLICT DO NOTHING", veterinarianId, 1);


		/*// Insertar permisos
		jdbcTemplate.update("INSERT IGNORE INTO permissions (id, name) VALUES (?, ?)", 1, "CREATE");
		jdbcTemplate.update("INSERT IGNORE INTO permissions (id, name) VALUES (?, ?)", 2, "READ");
		jdbcTemplate.update("INSERT IGNORE INTO permissions (id, name) VALUES (?, ?)", 3, "WRITE");
		jdbcTemplate.update("INSERT IGNORE INTO permissions (id, name) VALUES (?, ?)", 4, "DELETE");

		// Insertar roles
		jdbcTemplate.update("INSERT IGNORE INTO roles (id, role_name) VALUES (?, ?)", 1, "ADMIN");
		jdbcTemplate.update("INSERT IGNORE INTO roles (id, role_name) VALUES (?, ?)", 2, "USER");

		// Relacionar roles con permisos
		jdbcTemplate.update("INSERT IGNORE INTO role_permission (role_id, permission_id) VALUES (?, ?)", 1, 1);
		jdbcTemplate.update("INSERT IGNORE INTO role_permission (role_id, permission_id) VALUES (?, ?)", 1, 2);
		jdbcTemplate.update("INSERT IGNORE INTO role_permission (role_id, permission_id) VALUES (?, ?)", 1, 3);
		jdbcTemplate.update("INSERT IGNORE INTO role_permission (role_id, permission_id) VALUES (?, ?)", 1, 4);
		jdbcTemplate.update("INSERT IGNORE INTO role_permission (role_id, permission_id) VALUES (?, ?)", 2, 2);*/

		// Insertar usuario administrador
		/*jdbcTemplate.update("INSERT IGNORE INTO veterinarians (id, username, password, email, is_enabled, account_no_expired, account_no_locked, credentials_no_expired, last_name, specialty, professional_licence_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				100000, "admin", "$2a$10$Dxnp0wJeF8L9Ftd8./ExcuaJ62jKKVezB3gvBLcgo1qNOjAnR6QJi", "admin@example.com", true, true, true, true, "admin", "admin", "12345");*/

		/*Veterinarian veterinarian = new Veterinarian();
		veterinarian.setUsername("admin");
		veterinarian.setPassword("$2a$10$Dxnp0wJeF8L9Ftd8./ExcuaJ62jKKVezB3gvBLcgo1qNOjAnR6QJi");
		veterinarian.setEmail("admin@example.com");
		veterinarian.setEnabled(true);
		veterinarian.setAccountNoExpired(true);
		veterinarian.setAccountNoLocked(true);
		veterinarian.setCredentialsNoExpired(true);
		veterinarian.setLastName("admin");
		veterinarian.setSpecialty("admin");
		veterinarian.setProfessionalLicenceNumber("12345");

		Veterinarian savedVeterinarian = veterinarianRepository.save(veterinarian);
		Long veterinarianId = savedVeterinarian.getId();
		// Relacionar el usuario administrador con el rol ADMIN
		jdbcTemplate.update("INSERT IGNORE INTO users_roles (user_id, role_id) VALUES (?, ?)", veterinarianId, 1);*/
	}
}
