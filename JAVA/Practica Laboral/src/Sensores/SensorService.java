package Sensores;

import Database.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SensorService {
    private final Connection conn;

    public SensorService(Connection conn) {
        this.conn = conn;
    }

    public List<String> obtenerSensoresPorTipo(String tipo) throws SQLException {
        List<String> sensores = new ArrayList<>();
        String query = "SELECT id, ubicacion FROM sensores WHERE tipo = ?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tipo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String ubicacion = rs.getString("ubicacion");
                    sensores.add(id + " - " + ubicacion);
                }
            }
        }
        return sensores;
    }

    public List<String> obtenerSensoresPorEntorno(String entorno) throws SQLException {
        List<String> sensores = new ArrayList<>();
        String query = "SELECT id, tipo, ubicacion FROM sensores WHERE entorno = ?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, entorno);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String tipo = rs.getString("tipo");
                    String ubicacion = rs.getString("ubicacion");
                    sensores.add(id + " - " + tipo + " - " + ubicacion);
                }
            }
        }
        return sensores;
    }


    public boolean eliminarSensorPorId(int id) throws SQLException {
        String sql = "DELETE FROM sensores WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        }
    }
}
