package main;

import config.DatabaseConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection c = DatabaseConnection.getConnection()) {
            System.out.println("✔️ Conexión OK a la base de datos!");
        } catch (Exception e) {
            System.out.println("❌ Error de conexión:");
            e.printStackTrace();
        }
    }
}