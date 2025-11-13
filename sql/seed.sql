USE tfi_prog2;

INSERT INTO dispositivo_iot (serial, modelo, ubicacion, firmware_version)
VALUES ('SN-001', 'ESP32-CAM', 'Depósito', '1.0.3');

INSERT INTO configuracion_red (eliminado, ip, mascara, gateway, dns_primario, dhcp_habilitado, dispositivo_id)
VALUES (FALSE, '192.168.1.50', '255.255.255.0', '192.168.1.1', '8.8.8.8', FALSE, 1);