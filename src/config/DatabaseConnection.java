package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseConnection {

    private static final String PROPS = "/db.properties";
    private static String url = System.getProperty("db.url", "jdbc:mysql://localhost:3306/dbtpi3");
    private static String user = System.getProperty("db.user", "root");
    private static String password = System.getProperty("db.password", "");

    public static void setUrl(String url) {
        DatabaseConnection.url = url;
    }

    public static void setUser(String user) {
        DatabaseConnection.user = user;
    }

    public static void setPassword(String password) {
        DatabaseConnection.password = password;
    }
    
    
    /** 
     * Carga el driver JDBC
     * Valida que las credenciales sean correctas
     * Si no se encuentra lanza error de inicializador
     */
    static {
        try {
            // Carga explícita del driver (requerido en algunas versiones de Java)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Valida configuración tempranamente (fail-fast)
            validateConfiguration();
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Error: No se encontró el driver JDBC de MySQL: " + e.getMessage());
        } catch (IllegalStateException e) {
            throw new ExceptionInInitializerError("Error en la configuración de la base de datos: " + e.getMessage());
        }
    }
    
    /**
     * Valida que tanto el url, como el user y la contraseña sean válidas.
     * Si no son válidas, lanza un error.
     */
    private static void validateConfiguration() {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalStateException("La URL de la base de datos no está configurada");
        }
        if (user == null || user.trim().isEmpty()) {
            throw new IllegalStateException("El usuario de la base de datos no está configurado");
        }
        // PASSWORD puede ser vacío (común en MySQL local con usuario root sin contraseña)
        // Solo validamos que no sea null
        if (password == null) {
            throw new IllegalStateException("La contraseña de la base de datos no está configurada");
        }
    }
    
    public static Connection getConnection() throws Exception {
        
        Properties p = new Properties();

        // Carga el archivo db.properties desde /resources
        try (InputStream in = DatabaseConnection.class.getResourceAsStream(PROPS)) {
            if (in == null) {
                throw new IllegalStateException("No se encontró " + PROPS + " en el classpath.");
            }
            p.load(in);
        }

        //setea las credenciales
        setUrl(p.getProperty("db.url"));
        setUser(p.getProperty("db.user"));
        setPassword(p.getProperty("db.password"));

        return DriverManager.getConnection(url, user, password);
    }
}