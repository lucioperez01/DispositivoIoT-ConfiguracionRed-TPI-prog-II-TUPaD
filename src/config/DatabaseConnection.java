package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseConnection {

    private static final String PROPS = "/db.properties";

    public static Connection getConnection() throws Exception {

        Properties p = new Properties();

        // Carga el archivo db.properties desde /resources
        try (InputStream in = DatabaseConnection.class.getResourceAsStream(PROPS)) {
            if (in == null) {
                throw new IllegalStateException("No se encontró " + PROPS + " en el classpath.");
            }
            p.load(in);
        }

        String url = p.getProperty("db.url");
        String user = p.getProperty("db.user");
        String pass = p.getProperty("db.password");

        return DriverManager.getConnection(url, user, pass);
    }
}