package UI.VerEntorno;

import Sensores.SensorService;
import Database.ConnectionDB;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PanelEntorno extends JPanel {
    private final JComboBox<String> comboEntorno;
    private final DefaultListModel<String> modeloSensores;
    private final JList<String> listaSensores;

    private final SensorService sensorService;

    public PanelEntorno(String s) throws SQLException {
        sensorService = new SensorService(ConnectionDB.getConnection());

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Sensores por entorno"));

        // Combo para elegir entorno (urbano/rural)
        comboEntorno = new JComboBox<>(new String[]{"Interior", "Exterior"});
        comboEntorno.addActionListener(e -> cargarSensores());

        // Lista para mostrar sensores
        modeloSensores = new DefaultListModel<>();
        listaSensores = new JList<>(modeloSensores);
        JScrollPane scrollPane = new JScrollPane(listaSensores);

        // Estructura de panel
        add(comboEntorno, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        cargarSensores();
    }

    private void cargarSensores() {
        String entornoSeleccionado = (String) comboEntorno.getSelectedItem();
        if (entornoSeleccionado == null) return;

        try {
            modeloSensores.clear();
            List<String> sensores = sensorService.obtenerSensoresPorEntorno(entornoSeleccionado);
            for (String s : sensores) {
                modeloSensores.addElement(s);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar sensores: " + e.getMessage());
        }
    }
}
