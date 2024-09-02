-- Insertar permisos
INSERT INTO permissions (id, name) VALUES (1, 'CREATE') ON CONFLICT DO NOTHING;
INSERT INTO permissions (id, name) VALUES (2, 'READ') ON CONFLICT DO NOTHING;
INSERT INTO permissions (id, name) VALUES (3, 'WRITE') ON CONFLICT DO NOTHING;
INSERT INTO permissions (id, name) VALUES (4, 'DELETE') ON CONFLICT DO NOTHING;

-- Insertar roles
INSERT INTO roles (id, role_name) VALUES (1, 'ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO roles (id, role_name) VALUES (2, 'USER') ON CONFLICT DO NOTHING;

-- Relacionar roles con permisos
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1) ON CONFLICT DO NOTHING; -- ADMIN con CREATE
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 2) ON CONFLICT DO NOTHING; -- ADMIN con READ
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 3) ON CONFLICT DO NOTHING; -- ADMIN con WRITE
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 4) ON CONFLICT DO NOTHING; -- ADMIN con DELETE

INSERT INTO role_permission (role_id, permission_id) VALUES (2, 2) ON CONFLICT DO NOTHING; -- USER con READ


INSERT INTO users (id, username, password, email, is_enabled, account_no_expired, account_no_locked, credentials_no_expired) VALUES (100000, 'admin', '$2a$10$Dxnp0wJeF8L9Ftd8./ExcuaJ62jKKVezB3gvBLcgo1qNOjAnR6QJi', 'admin@example.com', true, true, true, true) ON CONFLICT DO NOTHING;
--INSERT INTO user_entity (id, username, password, email, is_enabled, account_no_expired, account_no_locked, credentials_no_expired, user_type, apellido, especialidad, tarjeta_profesional) VALUES (100000, 'admin', '$2a$10$Dxnp0wJeF8L9Ftd8./ExcuaJ62jKKVezB3gvBLcgo1qNOjAnR6QJi', 'admin@example.com', true, true, true, true, 'VETERINARIO', 'admin', 'veterinario', '12345');

-- Relacionar el usuario administrador con el rol ADMIN
INSERT INTO users_roles (user_id, role_id) VALUES (100000, 1) ON CONFLICT DO NOTHING; -- el rol ADMIN al usuario 'admin'


