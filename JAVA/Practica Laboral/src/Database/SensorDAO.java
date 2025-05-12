package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SensorDAO {
    public static void insertarSensor(Connection conn, String tipo, String ubicacion, String entorno) throws SQLException {
        String sql = "INSERT INTO sensores (tipo, ubicacion, entorno) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            stmt.setString(2, ubicacion);
            stmt.setString(3, entorno);
            stmt.executeUpdate();
        }
    }
}
