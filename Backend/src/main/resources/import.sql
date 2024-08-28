-- Insertar permisos
INSERT INTO permissions (id, name) VALUES (1, 'CREATE');
INSERT INTO permissions (id, name) VALUES (2, 'READ');
INSERT INTO permissions (id, name) VALUES (3, 'WRITE');
INSERT INTO permissions (id, name) VALUES (4, 'DELETE');

-- Insertar roles
INSERT INTO roles (id, role_name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, role_name) VALUES (2, 'USER');

-- Relacionar roles con permisos
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1); -- ADMIN con CREATE
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 2); -- ADMIN con READ
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 3); -- ADMIN con WRITE
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 4); -- ADMIN con DELETE

INSERT INTO role_permission (role_id, permission_id) VALUES (2, 2); -- USER con READ


INSERT INTO users (id, username, password, email, is_enabled, account_no_expired, account_no_locked, credentials_no_expired) VALUES (100000, 'admin', '$2a$10$Dxnp0wJeF8L9Ftd8./ExcuaJ62jKKVezB3gvBLcgo1qNOjAnR6QJi', 'admin@example.com', true, true, true, true);

-- Relacionar el usuario administrador con el rol ADMIN
INSERT INTO users_roles (user_id, role_id) VALUES (100000, 1); -- el rol ADMIN al usuario 'admin'


