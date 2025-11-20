package main;

import config.DatabaseConnection;
import dao.jdbc.JdbcDispositivoIoTDao;
import dao.jdbc.JdbcConfiguracionRedDao;
import entities.ConfiguracionRed;
import entities.DispositivoIoT;
import service.DispositivoIoTService;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class AppMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final JdbcDispositivoIoTDao dispositivoDao = new JdbcDispositivoIoTDao();
    private final JdbcConfiguracionRedDao configDao = new JdbcConfiguracionRedDao();
    private final DispositivoIoTService dispositivoService = new DispositivoIoTService();

    public void iniciar() {
        int opcion;
        do {
            mostrarOpciones();
            System.out.print("Opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> crearDispositivoConConfig();
                case 2 -> listarDispositivos();
                case 3 -> buscarPorId();
                case 4 -> buscarPorSerial();
                case 5 -> actualizarDispositivo();
                case 6 -> eliminarDispositivo();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }

            System.out.println();

        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println("================================");
        System.out.println("      MENÚ DISPOSITIVOS IoT     ");
        System.out.println("================================");
        System.out.println("1 - Crear dispositivo + configuración");
        System.out.println("2 - Listar dispositivos");
        System.out.println("3 - Buscar dispositivo por ID");
        System.out.println("4 - Buscar dispositivo por SERIAL");
        System.out.println("5 - Actualizar dispositivo");
        System.out.println("6 - Eliminar dispositivo (lógico)");
        System.out.println("0 - Salir");
        System.out.println("================================");
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese número válido: ");
            }
        }
    }

    private long leerLong() {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese número válido: ");
            }
        }
    }

    // ===================== CRUD =====================

    private void crearDispositivoConConfig() {
        try {
            System.out.println("=== Crear Dispositivo ===");

            DispositivoIoT d = new DispositivoIoT();

            System.out.print("Serial: ");
            d.setSerial(scanner.nextLine());

            System.out.print("Modelo: ");
            d.setModelo(scanner.nextLine());

            System.out.print("Ubicación: ");
            d.setUbicacion(scanner.nextLine());

            System.out.print("Versión Firmware: ");
            d.setFirmwareVersion(scanner.nextLine());

            System.out.println("--- Configuración de Red ---");
            ConfiguracionRed c = new ConfiguracionRed();

            System.out.print("IP: ");
            c.setIp(scanner.nextLine());

            System.out.print("Máscara: ");
            c.setMascara(scanner.nextLine());

            System.out.print("Gateway: ");
            c.setGateway(scanner.nextLine());

            System.out.print("DNS primario: ");
            c.setDnsPrimario(scanner.nextLine());

            System.out.print("DHCP habilitado? (s/n): ");
            c.setDhcpHabilitado(scanner.nextLine().trim().equalsIgnoreCase("s"));

            long id = dispositivoService.crearDispositivoConConfig(d, c);
            System.out.println("Dispositivo creado con ID: " + id);

        } catch (Exception e) {
            System.out.println("Error al crear dispositivo.");
            e.printStackTrace();
        }
    }

    private void listarDispositivos() {
        try (Connection conn = DatabaseConnection.getConnection()) {

            List<DispositivoIoT> lista = dispositivoDao.listar(conn);
            if (lista.isEmpty()) {
                System.out.println("No hay dispositivos registrados.");
                return;
            }

            System.out.println("=== Dispositivos Registrados ===");
            for (DispositivoIoT d : lista) {
                System.out.println(d);
            }

        } catch (Exception e) {
            System.out.println("Error al listar dispositivos.");
            e.printStackTrace();
        }
    }

    private void buscarPorId() {
        System.out.print("ID: ");
        long id = leerLong();

        try (Connection conn = DatabaseConnection.getConnection()) {

            DispositivoIoT d = dispositivoDao.buscarPorId(conn, id);
            if (d == null) {
                System.out.println("No encontrado.");
            } else {
                System.out.println(d);
            }

        } catch (Exception e) {
            System.out.println("Error al buscar.");
            e.printStackTrace();
        }
    }

    private void buscarPorSerial() {
        System.out.print("Serial: ");
        String serial = scanner.nextLine();

        try (Connection conn = DatabaseConnection.getConnection()) {

            DispositivoIoT d = dispositivoDao.buscarPorSerial(conn, serial);
            if (d == null) {
                System.out.println("No encontrado.");
            } else {
                System.out.println(d);
            }

        } catch (Exception e) {
            System.out.println("Error al buscar por serial.");
            e.printStackTrace();
        }
    }

    private void actualizarDispositivo() {
        System.out.print("ID a actualizar: ");
        long id = leerLong();

        try (Connection conn = DatabaseConnection.getConnection()) {

            DispositivoIoT d = dispositivoDao.buscarPorId(conn, id);
            if (d == null) {
                System.out.println("No existe ese ID.");
                return;
            }

            System.out.println("Ubicación actual: " + d.getUbicacion());
            System.out.print("Nueva ubicación: ");
            String ubic = scanner.nextLine();
            if (!ubic.isBlank()) d.setUbicacion(ubic);

            System.out.println("Firmware actual: " + d.getFirmwareVersion());
            System.out.print("Nuevo firmware: ");
            String fw = scanner.nextLine();
            if (!fw.isBlank()) d.setFirmwareVersion(fw);

            dispositivoDao.actualizar(conn, d);
            System.out.println("Actualizado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al actualizar.");
            e.printStackTrace();
        }
    }

    private void eliminarDispositivo() {
        System.out.print("ID a eliminar: ");
        long id = leerLong();

        try (Connection conn = DatabaseConnection.getConnection()) {

            dispositivoDao.eliminarLogico(conn, id);
            System.out.println("Eliminado lógicamente.");

        } catch (Exception e) {
            System.out.println("Error al eliminar.");
            e.printStackTrace();
        }
    }
}
