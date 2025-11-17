package main;

import config.DatabaseConnection;
import dao.jdbc.JdbcDispositivoIoTDao;
import dao.jdbc.JdbcConfiguracionRedDao;
import entities.DispositivoIoT;
import entities.ConfiguracionRed;
import java.sql.Connection;
import service.DispositivoIoTService;

public class Main {

    public static void main(String[] args) {
        
        try (Connection c = DatabaseConnection.getConnection()) {
            System.out.println("Conexión OK a la base de datos!");
        } catch (Exception e) {
            System.out.println("Error de conexión:");
            e.printStackTrace();
        }

        try {
            JdbcDispositivoIoTDao dispositivoDao = new JdbcDispositivoIoTDao();
            JdbcConfiguracionRedDao configDao = new JdbcConfiguracionRedDao();
            DispositivoIoTService service = new DispositivoIoTService();

            // Crear dispositivo IoT usando constructor simple
            DispositivoIoT d = new DispositivoIoT(
                    "SN-100",
                    "ESP32",
                    "Deposito",
                    "1.0.1"
            );

            // Crear configuración de red usando constructor simple
            ConfiguracionRed c = new ConfiguracionRed(
                    "192.168.0.90",
                    "255.255.255.0",
                    "192.168.0.1",
                    "8.8.8.8",
                    false
            );

            // Insertar ambos (service gestiona la relación)
            long idGenerado = service.crearDispositivoConConfig(d, c);

            System.out.println("✔ Dispositivo IoT creado con ID: " + idGenerado);

            // Mostrar datos
            System.out.println("\n=== LISTANDO DISPOSITIVOS ===");
            dispositivoDao.listar().forEach(System.out::println);

            System.out.println("\n=== BUSCANDO DISPOSITIVO ===");
            System.out.println(dispositivoDao.buscarPorId(idGenerado));

            // Actualizar ejemplo
            d.setId(idGenerado);
            d.setUbicacion("Laboratorio");
            dispositivoDao.actualizar(d);
            System.out.println("\n✔ Dispositivo actualizado");

            // Eliminación lógica
            dispositivoDao.eliminarLogico(idGenerado);
            System.out.println("\n✔ Dispositivo eliminado lógicamente");

            // Listado final
            System.out.println("\n=== LISTADO FINAL ===");
            dispositivoDao.listar().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
