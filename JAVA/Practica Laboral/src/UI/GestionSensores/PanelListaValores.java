package UI.GestionSensores;

import Sensores.SensorValorService;
import UI.GestionValores.GraficoValoresPanel;
import UI.GestionValores.ValorSensor;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PanelListaValores extends JPanel {
    private final SensorValorService valorService;
    private final JPanel panelGrafico; // Contenedor del gráfico actual

    public PanelListaValores(PanelSelectorSensor selector) throws SQLException {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Valores registrados"));

        valorService = new SensorValorService();
        panelGrafico = new JPanel(new BorderLayout());

        selector.listaSensores.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int sensorId = selector.getSensorSeleccionadoId();
                String nombre = selector.getSensorSeleccionadoNombre();
                cargarGrafico(sensorId, nombre);
            }
        });

        add(panelGrafico, BorderLayout.CENTER);
    }

    private void cargarGrafico(int sensorId, String nombreSensor) {
        panelGrafico.removeAll();

        if (sensorId == -1) {
            panelGrafico.revalidate();
            panelGrafico.repaint();
            return;
        }

        try {
            List<ValorSensor> valores = valorService.obtenerValoresGraficables(sensorId);
            if (valores.isEmpty()) {
                panelGrafico.add(new JLabel("No hay valores registrados."), BorderLayout.CENTER);
            } else {
                GraficoValoresPanel grafico = new GraficoValoresPanel(valores, nombreSensor);
                panelGrafico.add(grafico, BorderLayout.CENTER);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar valores: " + ex.getMessage());
        }

        panelGrafico.revalidate();
        panelGrafico.repaint();
    }
}
