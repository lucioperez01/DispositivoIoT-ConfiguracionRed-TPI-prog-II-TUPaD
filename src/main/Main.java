package main;

import config.DatabaseConnection;
import dao.jdbc.JdbcDispositivoIoTDao;
import entities.ConfiguracionRed;
import entities.DispositivoIoT;
import service.DispositivoIoTService;

import java.sql.Connection;

public class Main {

	public static void main(String[] args) {

		System.out.println("Verificando conexión a MySQL...");
		try (Connection conn = DatabaseConnection.getConnection()) {
			System.out.println("Conexión establecida correctamente.\n");
		} catch (Exception e) {
			System.out.println("No se pudo conectar a MySQL");
			e.printStackTrace();
			return;
		}

		try {

			DispositivoIoTService service = new DispositivoIoTService();
			JdbcDispositivoIoTDao dao = new JdbcDispositivoIoTDao();

			// 1) CREAR DOS DISPOSITIVOS
			DispositivoIoT d1 = new DispositivoIoT("SN-1004", "ESP32-CAM", "Laboratorio", "2.0.1");
			ConfiguracionRed c1 = new ConfiguracionRed(null, false,
					"192.168.0.50", "255.255.255.0", "192.168.0.1",
					"8.8.8.8", false);

			DispositivoIoT d2 = new DispositivoIoT("SN-1005", "ESP8266", "Depósito", "1.1.0");
			ConfiguracionRed c2 = new ConfiguracionRed(null, false,
					"192.168.0.51", "255.255.255.0", "192.168.0.1",
					"8.8.4.4", false);

			long id1 = service.crearDispositivoConConfig(d1, c1);
			long id2 = service.crearDispositivoConConfig(d2, c2);

			System.out.println("Dispositivos creados con IDs: " + id1 + " y " + id2 + "\n");


			// LISTAR TODOS
			try (Connection conn = DatabaseConnection.getConnection()) {
				System.out.println("=== LISTANDO DISPOSITIVOS ===");
				dao.listar(conn).forEach(System.out::println);
			}

			// BUSCAR POR ID
			try (Connection conn = DatabaseConnection.getConnection()) {
				System.out.println("\n=== BUSCANDO DISPOSITIVO ID " + id1 + " ===");
				System.out.println(dao.buscarPorId(conn, id1));
			}

			// ACTUALIZAR
			try (Connection conn = DatabaseConnection.getConnection()) {

				DispositivoIoT actualizar = dao.buscarPorId(conn, id1);
				actualizar.setUbicacion("Oficina Central");
				actualizar.setFirmwareVersion("2.1.0");

				dao.actualizar(conn, actualizar);

				System.out.println("\nDispositivo actualizado.\n");
			}

			// ELIMINACIÓN LÓGICA
			try (Connection conn = DatabaseConnection.getConnection()) {
				dao.eliminarLogico(conn, id2);
				System.out.println("Dispositivo id " + id2 + " eliminado lógicamente.\n");
			}

			// LISTADO FINAL
			try (Connection conn = DatabaseConnection.getConnection()) {
				System.out.println("=== LISTADO FINAL ===");
				dao.listar(conn).forEach(System.out::println);
			}

		} catch (Exception e) {
			System.out.println("ERROR:");
			e.printStackTrace();
		}
	}
}
