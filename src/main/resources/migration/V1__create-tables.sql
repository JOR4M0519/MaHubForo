CREATE TABLE usuarios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          correo_electronico VARCHAR(100) UNIQUE NOT NULL,
                          contrasenia VARCHAR(300) NOT NULL
);

CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    mensaje VARCHAR(1000) NOT NULL,
    fecha_de_creacion DATETIME NOT NULL,
    curso VARCHAR(100) NOT NULL,
    autor_id BIGINT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);

CREATE TABLE respuestas (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            mensaje VARCHAR(1000) NOT NULL,
                            fecha_de_creacion DATETIME NOT NULL,
                            autor_id BIGINT NOT NULL,
                            topico_id BIGINT NOT NULL,
                            FOREIGN KEY (autor_id) REFERENCES usuarios(id),
                            FOREIGN KEY (topico_id) REFERENCES topicos(id)
);
