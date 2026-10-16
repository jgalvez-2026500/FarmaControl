-- =====================================================================
-- FarmaControl - Control de Medicamentos de Enfermería
-- =====================================================================
DROP DATABASE IF EXISTS FarmaControl_db;
CREATE DATABASE FarmaControl_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE FarmaControl_db;

-- Usuario de la aplicación (coincide con config/Enviroment.java)
CREATE USER IF NOT EXISTS 'farmacontrol'@'localhost' IDENTIFIED BY 'farma123';
GRANT ALL PRIVILEGES ON FarmaControl_db.* TO 'farmacontrol'@'localhost';
FLUSH PRIVILEGES;

-- =====================================================================
-- HU 1 | Tarea 1.1  USUARIO
-- =====================================================================
CREATE TABLE usuario (
    id_usuario      INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario  VARCHAR(50)  NOT NULL UNIQUE,
    clave           CHAR(64)     NOT NULL,              -- SHA2-256 en hexadecimal
    nombre_completo VARCHAR(100) NOT NULL,
    user_status     BOOLEAN      NOT NULL DEFAULT TRUE  -- TRUE = cuenta activa
);

DELIMITER $$

CREATE PROCEDURE sp_login_usuario(
    IN p_nombre_usuario VARCHAR(50),
    IN p_clave          VARCHAR(255)
)
BEGIN
    SELECT id_usuario, nombre_usuario, nombre_completo, user_status
    FROM usuario
    WHERE nombre_usuario = p_nombre_usuario
      AND clave = SHA2(p_clave, 256);
END $$

DELIMITER ;

-- =====================================================================
-- HU 2 | Tarea 2.1  LABORATORIO
-- =====================================================================
CREATE TABLE laboratorio (
    id_laboratorio INT AUTO_INCREMENT PRIMARY KEY,
    nombre_lab     VARCHAR(100) NOT NULL UNIQUE,
    pais_origen    VARCHAR(60)  NOT NULL
);

-- =====================================================================
-- HU 3 | Tarea 3.1  MEDICAMENTO
-- (fecha_caducidad se agrega para cumplir el "control de caducidad" de la épica)
-- =====================================================================
CREATE TABLE medicamento (
    id_medicamento  INT AUTO_INCREMENT PRIMARY KEY,
    nombre          VARCHAR(100)  NOT NULL,
    miligramos      DECIMAL(8,2)  NOT NULL,
    cantidad_stock  INT           NOT NULL,
    fecha_caducidad DATE          NOT NULL,
    id_laboratorio  INT           NOT NULL,
    CONSTRAINT chk_miligramos CHECK (miligramos > 0),
    CONSTRAINT chk_stock      CHECK (cantidad_stock >= 0),
    CONSTRAINT fk_med_lab FOREIGN KEY (id_laboratorio)
        REFERENCES laboratorio(id_laboratorio)
);

-- Tarea 3.2  CRUD transaccional
DELIMITER $$

CREATE PROCEDURE sp_crear_medicamento(
    IN p_nombre          VARCHAR(100),
    IN p_miligramos      DECIMAL(8,2),
    IN p_cantidad_stock  INT,
    IN p_fecha_caducidad DATE,
    IN p_id_laboratorio  INT
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    START TRANSACTION;
        INSERT INTO medicamento(nombre, miligramos, cantidad_stock, fecha_caducidad, id_laboratorio)
        VALUES (p_nombre, p_miligramos, p_cantidad_stock, p_fecha_caducidad, p_id_laboratorio);
    COMMIT;
END $$

CREATE PROCEDURE sp_actualizar_medicamento(
    IN p_id_medicamento  INT,
    IN p_nombre          VARCHAR(100),
    IN p_miligramos      DECIMAL(8,2),
    IN p_cantidad_stock  INT,
    IN p_fecha_caducidad DATE,
    IN p_id_laboratorio  INT
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    START TRANSACTION;
        UPDATE medicamento
        SET nombre = p_nombre,
            miligramos = p_miligramos,
            cantidad_stock = p_cantidad_stock,
            fecha_caducidad = p_fecha_caducidad,
            id_laboratorio = p_id_laboratorio
        WHERE id_medicamento = p_id_medicamento;
    COMMIT;
END $$

CREATE PROCEDURE sp_eliminar_medicamento(
    IN p_id_medicamento INT
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    START TRANSACTION;
        DELETE FROM medicamento WHERE id_medicamento = p_id_medicamento;
    COMMIT;
END $$

DELIMITER ;

-- =====================================================================
-- HU 4 | Tarea 4.1  VISTA con INNER JOIN
-- =====================================================================
CREATE VIEW view_read_medicamento AS
SELECT
    m.id_medicamento,
    m.nombre,
    m.miligramos,
    m.cantidad_stock,
    m.fecha_caducidad,
    m.id_laboratorio,
    l.nombre_lab
FROM medicamento m
INNER JOIN laboratorio l ON m.id_laboratorio = l.id_laboratorio;