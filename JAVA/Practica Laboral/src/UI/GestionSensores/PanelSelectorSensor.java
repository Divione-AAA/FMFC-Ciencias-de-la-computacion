package UI.GestionSensores;

import Sensores.SensorService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PanelSelectorSensor extends JPanel {
    public JComboBox<String> comboTipos;
    public JList<String> listaSensores;
    private final DefaultListModel<String> modelo;

    private final SensorService sensorService;

    public PanelSelectorSensor() throws SQLException {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Sensores disponibles"));

        sensorService = new SensorService(Database.ConnectionDB.getConnection());

        comboTipos = new JComboBox<>(new String[]{
                "Termómetro", "Higrómetro", "Calidad del aire", "Sonómetro", "Radiómetro", "Pluviómetro"
        });
        comboTipos.addActionListener(e -> refrescarLista());

        modelo = new DefaultListModel<>();
        listaSensores = new JList<>(modelo);
        listaSensores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(comboTipos, BorderLayout.NORTH);
        add(new JScrollPane(listaSensores), BorderLayout.CENTER);

        refrescarLista();
    }

    public void refrescarLista() {
        try {
            modelo.clear();
            List<String> sensores = sensorService.obtenerSensoresPorTipo((String) comboTipos.getSelectedItem());
            for (String s : sensores) modelo.addElement(s);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar sensores: " + e.getMessage());
        }
    }

    public int getSensorSeleccionadoId() {
        String seleccion = listaSensores.getSelectedValue();
        if (seleccion != null) {
            return Integer.parseInt(seleccion.split(" ")[0]);
        }
        return -1;
    }

    public String getSensorSeleccionadoNombre() {
        String seleccion = listaSensores.getSelectedValue();
        if (seleccion != null && seleccion.contains(" - ")) {
            return seleccion.substring(seleccion.indexOf(" - ") + 3);
        }
        return "";
    }
}
