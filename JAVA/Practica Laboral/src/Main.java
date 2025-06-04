import UI.GestionUsuario.LoginUI;
import com.formdev.flatlaf.FlatLightLaf;
import GestionUsuario.Crearuser;

//las declaro en la otra clase por lo q aqui no lo use

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear tablas
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db")) {
                String sql = """
                    CREATE TABLE IF NOT EXISTS sensores (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        tipo TEXT NOT NULL,
                        ubicacion TEXT NOT NULL,
                        entorno TEXT NOT NULL
                    );
                """;
                conn.createStatement().executeUpdate(sql);
                System.out.println("✅ Tabla 'sensores' verificada o creada correctamente.");
                String valoresSql = """
                    CREATE TABLE IF NOT EXISTS valores_sensor (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        sensor_id INTEGER NOT NULL,
                        valor REAL NOT NULL,
                        timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY(sensor_id) REFERENCES sensores(id) ON DELETE CASCADE
                    );
                """;
                String alertasSql = """
                    DROP TABLE IF EXISTS alertas;
                    CREATE TABLE alertas (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nombre TEXT NOT NULL,
                        sensor_id INTEGER NOT NULL,
                        tipo TEXT NOT NULL,
                        limite REAL NOT NULL,
                        FOREIGN KEY(sensor_id) REFERENCES sensores(id) ON DELETE CASCADE
                    );


               \s""";
                conn.createStatement().executeUpdate(alertasSql);
                conn.createStatement().executeUpdate(valoresSql);
                //System.out.println("✅ Tabla 'valores_sensor' verificada o creada correctamente.");

            }

            // Verificar existencia de tabla
            /*try (Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db")) {
                ResultSet rs = conn.getMetaData().getTables(null, null, "sensores", null);
                System.out.println(rs.next() ? "✅ La tabla 'sensores' existe." : "❌ La tabla 'sensores' NO existe.");
            }*/

            // Crear usuario admin
            FlatLightLaf.setup();
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db")) {
                Crearuser.crearUsuario(conn, "Otro", "1234", "admin");
            }

            // Iniciar Login
            LoginUI.main(args);

        } catch (SQLException e) {
            System.err.println("Error de base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}
