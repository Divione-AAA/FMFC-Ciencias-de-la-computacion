package Servicios;

import Database.ConnectionDB;
import Modelos.Alerta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertaService {

    public void guardarAlerta(Alerta alerta) throws SQLException {
        String sql = "INSERT INTO alertas (sensor_id, tipo, limite, nombre) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, alerta.sensorId);
            stmt.setString(2, alerta.tipo);
            stmt.setDouble(3, alerta.limite);
            stmt.setString(4, alerta.nombre); // Asegúrate de que alerta.nombre no sea null
            stmt.executeUpdate();
        }
    }

    public List<Alerta> obtenerAlertasPorSensor(int sensorId) throws SQLException {
        List<Alerta> alertas = new ArrayList<>();
        String sql = "SELECT sensor_id, tipo, limite, nombre FROM alertas WHERE sensor_id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sensorId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("sensor_id");
                    String tipo = rs.getString("tipo");
                    double limite = rs.getDouble("limite");
                    String nombre = rs.getString("nombre");
                    Alerta alerta = new Alerta(id, tipo, limite,nombre);
                    alerta.nombre = nombre;
                    alertas.add(alerta);
                }
            }
        }
        return alertas;
    }

    public List<Alerta> obtenerTodasAlertas() throws SQLException {
        List<Alerta> alertas = new ArrayList<>();
        String sql = "SELECT sensor_id, tipo, limite, nombre FROM alertas";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("sensor_id");
                String tipo = rs.getString("tipo");
                double limite = rs.getDouble("limite");
                String nombre = rs.getString("nombre");
                Alerta alerta = new Alerta(id, tipo, limite,nombre);
                alerta.nombre = nombre;
                alertas.add(alerta);
            }
        }
        return alertas;
    }

    public List<Alerta> verificarAlertas() throws SQLException {
        List<Alerta> alertasActivas = new ArrayList<>();

        String sql = """
        SELECT a.sensor_id, a.tipo AS tipo_alerta, a.limite, s.tipo AS nombre, s.ubicacion, s.entorno,
               (SELECT valor FROM valores_sensor v WHERE v.sensor_id = s.id ORDER BY v.timestamp DESC LIMIT 1) AS ultimo_valor
        FROM alertas a
        JOIN sensores s ON a.sensor_id = s.id
        """;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                double valorActual = rs.getDouble("ultimo_valor");
                double limite = rs.getDouble("limite");

                if (valorActual > limite) {
                    Alerta alerta = new Alerta(
                            rs.getInt("sensor_id"),
                            rs.getString("tipo_alerta"),
                            limite,
                            rs.getString("nombre"),
                            rs.getString("ubicacion"),
                            rs.getString("entorno"),
                            valorActual
                    );
                    alertasActivas.add(alerta);
                }
            }
        }

        return alertasActivas;
    }
}
