

-- Inserción de cursos
INSERT INTO cursos (nombre, creditos)
VALUES ('Programer', 5);

INSERT INTO cursos (nombre, creditos)
VALUES ('Developer', 5);

INSERT INTO cursos (nombre, creditos)
VALUES ('Expert', 5);

-- Inserción de usuario admin
INSERT INTO users (id, username, password, enabled)
VALUES (1, 'admin', '$2y$10$vuI0HcLrVGkj3.f6EKwB8Oj9M7MPQz2puArt6sXp7P.72kUXKW6/m', true);

-- Inserción de rol
INSERT INTO authorities (id, userid, authority)
VALUES (1, 1, 'ROLE_ADMIN');
