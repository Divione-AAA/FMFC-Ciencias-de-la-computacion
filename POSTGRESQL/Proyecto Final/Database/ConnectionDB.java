package Database;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/edja_camaguey";
    private static final String DRIVER_CLASS = "org.postgresql.Driver";
    private static final String DRIVER_JAR = "libs/postgresql-42.7.11.jar";

    // Cambiado a dinámico
    private static String user = null;
    private static String password = null;

    static {
        cargarDriver();
    }

    private static void cargarDriver() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException ex) {
            File driverJar = Paths.get(DRIVER_JAR).toFile();
            if (driverJar.exists()) {
                try {
                    URLClassLoader loader = new URLClassLoader(
                            new URL[]{driverJar.toURI().toURL()},
                            ConnectionDB.class.getClassLoader()
                    );
                    Thread.currentThread().setContextClassLoader(loader);
                    Class.forName(DRIVER_CLASS, true, loader);
                } catch (Exception inner) {
                    throw new ExceptionInInitializerError(inner);
                }
            } else {
                throw new ExceptionInInitializerError(ex);
            }
        }
    }

    // Configurar las credenciales desde el Login
    public static void setCredentials(String username, String pass) {
        user = username;
        password = pass;
    }

    // Intentar una conexión explícita de prueba
    public static Connection testConnection(String username, String pass) throws SQLException {
        return DriverManager.getConnection(URL, username, pass);
    }

    public static Connection getConnection() throws SQLException {
        if (user == null || password == null) {
            throw new SQLException("Credenciales de base de datos no inicializadas.");
        }
        return DriverManager.getConnection(URL, user, password);
    }
}