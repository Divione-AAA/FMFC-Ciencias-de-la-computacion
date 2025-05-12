package Sensores;

import Database.ConnectionDB;
import UI.GestionValores.ValorSensor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SensorValorService {

    // Método para obtener datos para graficar
    public List<ValorSensor> obtenerValoresGraficables(int sensorId) throws SQLException {
        List<ValorSensor> valores = new ArrayList<>();

        String sql = "SELECT timestamp, valor FROM valores_sensor WHERE sensor_id = ? ORDER BY timestamp ASC";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sensorId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    double valor = rs.getDouble("valor");
                    valores.add(new ValorSensor(new java.util.Date(timestamp.getTime()), valor));
                }
            }
        }

        return valores;
    }

    // Método para agregar un nuevo valor
    public void agregarValor(int sensorId, double valor) throws SQLException {
        String sql = "INSERT INTO valores_sensor (sensor_id, valor) VALUES (?, ?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sensorId);
            stmt.setDouble(2, valor);
            stmt.executeUpdate();
        }
    }

    // Método para obtener la lista simple de valores como String
    public List<String> obtenerValores(int sensorId) throws SQLException {
        List<String> valores = new ArrayList<>();
        String sql = "SELECT valor, timestamp FROM valores_sensor WHERE sensor_id = ? ORDER BY timestamp ASC";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sensorId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    double valor = rs.getDouble("valor");
                    String timestamp = rs.getString("timestamp");
                    valores.add(timestamp + " -> " + valor);
                }
            }
        }
        return valores;
    }
}
