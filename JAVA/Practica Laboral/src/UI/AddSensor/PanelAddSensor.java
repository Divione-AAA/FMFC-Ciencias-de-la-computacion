package UI.AddSensor;

import Database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;

public class PanelAddSensor extends JPanel {
    private final JTextField campoUbicacion;
    private final JComboBox<String> comboTipoSensor;
    private final JComboBox<String> comboEntorno;

    public PanelAddSensor(String titulo) {
        setLayout(new BorderLayout());

        JLabel etiquetaTitulo = new JLabel(titulo, SwingConstants.CENTER);
        etiquetaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        etiquetaTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        //add(etiquetaTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        panelCentro.setOpaque(false);

        Font fuenteCampos = new Font("Segoe UI", Font.PLAIN, 16);
        Dimension campoDimension = new Dimension(250, 40);

        // Etiqueta y comboTipoSensor
        JLabel labelTipo = new JLabel("Tipo de sensor:");
        labelTipo.setFont(fuenteCampos);
        labelTipo.setAlignmentX(Component.CENTER_ALIGNMENT);

        comboTipoSensor = new JComboBox<>(new String[]{
                "Termómetro", "Higrómetro", "Calidad del aire", "Sonómetro", "Radiómetro", "Pluviómetro"
        });
        comboTipoSensor.setMaximumSize(campoDimension);
        comboTipoSensor.setFont(fuenteCampos);
        comboTipoSensor.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Etiqueta y campoUbicacion
        JLabel labelUbicacion = new JLabel("Ubicación:");
        labelUbicacion.setFont(fuenteCampos);
        labelUbicacion.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoUbicacion = new JTextField();
        campoUbicacion.setMaximumSize(campoDimension);
        campoUbicacion.setFont(fuenteCampos);
        campoUbicacion.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Etiqueta y comboEntorno
        JLabel labelEntorno = new JLabel("Entorno:");
        labelEntorno.setFont(fuenteCampos);
        labelEntorno.setAlignmentX(Component.CENTER_ALIGNMENT);

        comboEntorno = new JComboBox<>(new String[]{"Interior", "Exterior"});
        comboEntorno.setMaximumSize(campoDimension);
        comboEntorno.setFont(fuenteCampos);
        comboEntorno.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botón guardar
        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonGuardar.setPreferredSize(new Dimension(160, 45));
        botonGuardar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botonGuardar.putClientProperty("JButton.arc", 20);
        botonGuardar.putClientProperty("JButton.buttonType", "roundRect");

        try {
            ImageIcon iconoOriginal = new ImageIcon(ClassLoader.getSystemResource("resources/add.png"));
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            botonGuardar.setIcon(new ImageIcon(imagenEscalada));
        } catch (Exception e) {
            System.err.println("No se pudo cargar el ícono: " + e.getMessage());
        }

        botonGuardar.addActionListener((ActionEvent e) -> guardarSensor());

        // Añadir componentes al panelCentro
        panelCentro.add(etiquetaTitulo);
        panelCentro.add(labelTipo);
        panelCentro.add(comboTipoSensor);
        panelCentro.add(Box.createVerticalStrut(15));
        panelCentro.add(labelUbicacion);
        panelCentro.add(campoUbicacion);
        panelCentro.add(Box.createVerticalStrut(15));
        panelCentro.add(labelEntorno);
        panelCentro.add(comboEntorno);
        panelCentro.add(Box.createVerticalStrut(25));
        panelCentro.add(botonGuardar);

        // Centrado absoluto
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.add(panelCentro);
        wrapper.setOpaque(false);
        add(wrapper, BorderLayout.CENTER);
    }

    private void guardarSensor() {
        String tipo = (String) comboTipoSensor.getSelectedItem();
        String ubicacion = campoUbicacion.getText().trim();
        String entorno = (String) comboEntorno.getSelectedItem();

        if (ubicacion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese la ubicación.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConnectionDB.getConnection()) {
            SensorDAO.insertarSensor(conn, tipo, ubicacion, entorno);
            JOptionPane.showMessageDialog(this, "Sensor guardado con éxito.");
            campoUbicacion.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar sensor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
