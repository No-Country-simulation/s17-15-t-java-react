package com.gestor_clinica_veterinaria.VeterinaryHospitalManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class VeterinaryHospitalManagerApplication implements CommandLineRunner {
	@Autowired
	private JdbcTemplate jdbcTemplate;


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
		jdbcTemplate.update("INSERT INTO users (id, username, password, email, is_enabled, account_no_expired, account_no_locked, credentials_no_expired) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT (id) DO NOTHING",
				100000, "admin", "$2a$10$Dxnp0wJeF8L9Ftd8./ExcuaJ62jKKVezB3gvBLcgo1qNOjAnR6QJi", "admin@example.com", true, true, true, true);

		// Relacionar el usuario administrador con el rol ADMIN
		jdbcTemplate.update("INSERT INTO users_roles (user_id, role_id) VALUES (?, ?) ON CONFLICT DO NOTHING", 100000, 1);
	}
}
