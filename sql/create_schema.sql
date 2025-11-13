CREATE DATABASE IF NOT EXISTS tfi_prog2 CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE tfi_prog2;

CREATE TABLE dispositivo_iot (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  eliminado BOOLEAN NOT NULL DEFAULT FALSE,
  serial VARCHAR(50) NOT NULL UNIQUE,
  modelo VARCHAR(50) NOT NULL,
  ubicacion VARCHAR(120),
  firmware_version VARCHAR(30)
);

CREATE TABLE configuracion_red (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  eliminado BOOLEAN NOT NULL DEFAULT FALSE,
  ip VARCHAR(45),
  mascara VARCHAR(45),
  gateway VARCHAR(45),
  dns_primario VARCHAR(45),
  dhcp_habilitado BOOLEAN NOT NULL,
  dispositivo_id BIGINT NOT NULL UNIQUE,
  CONSTRAINT fk_confred_dispo FOREIGN KEY (dispositivo_id)
    REFERENCES dispositivo_iot(id)
    ON DELETE CASCADE
);