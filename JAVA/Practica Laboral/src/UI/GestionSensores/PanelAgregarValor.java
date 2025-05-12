package UI.GestionSensores;

import Sensores.SensorValorService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PanelAgregarValor extends JPanel {
    private final JTextField campoValor;
    private final SensorValorService valorService;

    public PanelAgregarValor(PanelSelectorSensor selector) throws SQLException {
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createTitledBorder("Añadir valor al sensor"));

        campoValor = new JTextField(10);
        JButton botonAgregar = new JButton("Agregar");

        valorService = new SensorValorService();

        botonAgregar.addActionListener(e -> {
            try {
                int idSensor = selector.getSensorSeleccionadoId();
                if (idSensor == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona un sensor.");
                    return;
                }

                double valor = Double.parseDouble(campoValor.getText());
                valorService.agregarValor(idSensor, valor);
                JOptionPane.showMessageDialog(this, "Valor agregado.");
                campoValor.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Introduce un valor numérico.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar valor: " + ex.getMessage());
            }
        });

        add(new JLabel("Valor:"));
        add(campoValor);
        add(botonAgregar);
    }
}
