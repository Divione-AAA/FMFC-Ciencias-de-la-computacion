package UI.DelSensor;

import Sensores.*;
import Database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class PanelDelSensor extends JPanel {
    private final JComboBox<String> comboTipos;
    private final DefaultListModel<String> modeloSensores;
    private final JList<String> listaSensores;
    private final SensorService sensorService;

    public PanelDelSensor(String rol) throws SQLException {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        sensorService = new SensorService(ConnectionDB.getConnection());

        JLabel etiquetaTitulo = new JLabel("Eliminar sensores", SwingConstants.CENTER);
        etiquetaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        etiquetaTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(etiquetaTitulo, BorderLayout.NORTH);

        // Panel izquierdo con combo y lista
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBackground(Color.WHITE);
        panelIzquierdo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 4, 4, new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        comboTipos = new JComboBox<>(new String[]{
                "Termómetro", "Higrómetro", "Calidad del aire", "Sonómetro", "Radiómetro", "Pluviómetro"
        });
        comboTipos.setMaximumSize(new Dimension(250, 35));
        comboTipos.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        comboTipos.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboTipos.addActionListener(e -> refrescarSensores());

        modeloSensores = new DefaultListModel<>();
        listaSensores = new JList<>(modeloSensores);
        listaSensores.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(listaSensores);
        scrollPane.setPreferredSize(new Dimension(250, 200));

        panelIzquierdo.add(comboTipos);
        panelIzquierdo.add(Box.createVerticalStrut(15));
        panelIzquierdo.add(scrollPane);

        // Panel con los dos botones centrados
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelBotones.setOpaque(false);

        // Botón Eliminar
        JButton botonEliminar = new JButton("Eliminar");
        botonEliminar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        botonEliminar.setPreferredSize(new Dimension(250, 60)); // Botón más grande
        botonEliminar.putClientProperty("JButton.arc", 20);
        botonEliminar.putClientProperty("JButton.buttonType", "roundRect");

        try {
            // Usando getClass().getResource para cargar la imagen
            ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/resources/delete.png"));
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            botonEliminar.setIcon(new ImageIcon(imagenEscalada));
        } catch (Exception e) {
            System.err.println("No se pudo cargar el ícono de eliminar: " + e.getMessage());
        }

        botonEliminar.addActionListener((ActionEvent e) -> {
            try {
                eliminarSensor();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Botón Refrescar
        JButton botonRefrescar = new JButton("Refrescar");
        botonRefrescar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        botonRefrescar.setPreferredSize(new Dimension(250, 60)); // Botón más grande
        botonRefrescar.putClientProperty("JButton.arc", 20);
        botonRefrescar.putClientProperty("JButton.buttonType", "roundRect");

        try {
            // Usando getClass().getResource para cargar la imagen
            ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/resources/refresh.png"));
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            botonRefrescar.setIcon(new ImageIcon(imagenEscalada));
        } catch (Exception e) {
            System.err.println("No se pudo cargar el ícono de refrescar: " + e.getMessage());
        }

        botonRefrescar.addActionListener(e -> refrescarSensores());

        // Añadir los botones al panel de botones
        panelBotones.add(botonEliminar);
        panelBotones.add(botonRefrescar);

        // Panel contenedor central (izquierda y derecha)
        JPanel contenedorCentro = new JPanel(new BorderLayout(30, 0));
        contenedorCentro.setOpaque(false);
        contenedorCentro.add(panelIzquierdo, BorderLayout.WEST);
        contenedorCentro.add(panelBotones, BorderLayout.CENTER);

        add(contenedorCentro, BorderLayout.CENTER);

        refrescarSensores(); // Inicial
    }

    private void refrescarSensores() {
        try {
            modeloSensores.clear();
            String tipoSeleccionado = (String) comboTipos.getSelectedItem();
            List<String> sensores = sensorService.obtenerSensoresPorTipo(tipoSeleccionado);
            for (String sensor : sensores) {
                modeloSensores.addElement(sensor);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al refrescar sensores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarSensor() throws SQLException {
        String sensorSeleccionado = listaSensores.getSelectedValue();
        if (sensorSeleccionado != null && !sensorSeleccionado.isEmpty()) {
            int id = Integer.parseInt(sensorSeleccionado.split(" ")[0]);
            boolean eliminado = sensorService.eliminarSensorPorId(id);
            if (eliminado) {
                modeloSensores.removeElement(sensorSeleccionado);
                JOptionPane.showMessageDialog(this, "Sensor eliminado con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el sensor.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un sensor.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}
