package UI.VerSensor;

import Sensores.SensorService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PanelPorSensor extends JPanel {
    private final JComboBox<String> comboTipos;
    private final JList<String> listaSensores;
    private final DefaultListModel<String> modeloSensores;
    private final SensorService sensorService;

    public PanelPorSensor(String s) throws SQLException {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Sensores por Tipo"));

        sensorService = new SensorService(Database.ConnectionDB.getConnection());

        // Combo box para seleccionar tipo de sensor
        comboTipos = new JComboBox<>(new String[]{
                "Termómetro", "Higrómetro", "Calidad del aire", "Sonómetro", "Radiómetro", "Pluviómetro"
        });
        comboTipos.addActionListener(e -> cargarSensores());

        // Lista de sensores
        modeloSensores = new DefaultListModel<>();
        listaSensores = new JList<>(modeloSensores);
        JScrollPane scrollPane = new JScrollPane(listaSensores);

        // Añadir componentes
        add(comboTipos, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar datos iniciales
        cargarSensores();
    }

    private void cargarSensores() {
        String tipoSeleccionado = (String) comboTipos.getSelectedItem();
        modeloSensores.clear();

        try {
            List<String> sensores = sensorService.obtenerSensoresPorTipo(tipoSeleccionado);
            if (sensores.isEmpty()) {
                modeloSensores.addElement("No hay sensores registrados para este tipo.");
            } else {
                for (String s : sensores) {
                    modeloSensores.addElement(s);
                }
            }
        } catch (SQLException e) {
            modeloSensores.addElement("Error al cargar sensores: " + e.getMessage());
        }
    }
}
