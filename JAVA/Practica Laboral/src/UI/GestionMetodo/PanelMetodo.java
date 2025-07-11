package UI.GestionMetodo;

import Modelos.Alerta;
import Sensores.SensorService;
import Servicios.AlertaService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PanelMetodo extends JPanel {
    private final JComboBox<String> comboTipos;
    private final JList<String> listaSensores;
    private final DefaultListModel<String> modeloSensores;

    private final DefaultListModel<String> modeloAlertas;
    private final JList<String> listaAlertas;

    private final JTextField campoLimite;
    private final JButton botonGuardar;

    private final AlertaService alertaService;
    private final SensorService sensorService;

    public PanelMetodo(String rol) throws SQLException {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Gestión de Alertas"));

        // Verificación de rol
        if (!"admin".equalsIgnoreCase(rol)) {
            JLabel mensaje = new JLabel("Acceso denegado. Solo disponible para administradores.", SwingConstants.CENTER);
            mensaje.setFont(new Font("Segoe UI", Font.BOLD, 18));
            add(mensaje, BorderLayout.CENTER);
            // Inicializamos las variables a null o vacías para evitar errores posteriores
            comboTipos = null;
            listaSensores = null;
            modeloSensores = null;
            modeloAlertas = null;
            listaAlertas = null;
            campoLimite = null;
            botonGuardar = null;
            alertaService = null;
            sensorService = null;
            return;
        }

        alertaService = new AlertaService();
        sensorService = new SensorService(Database.ConnectionDB.getConnection());

        comboTipos = new JComboBox<>(new String[]{
                "Termómetro", "Higrómetro", "Calidad del aire", "Sonómetro", "Radiómetro", "Pluviómetro"
        });
        comboTipos.addActionListener(e -> cargarSensores());

        modeloSensores = new DefaultListModel<>();
        listaSensores = new JList<>(modeloSensores);
        listaSensores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaSensores.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarAlertasSensor();
        });

        JPanel panelIzquierda = new JPanel(new BorderLayout(5, 5));
        panelIzquierda.add(comboTipos, BorderLayout.NORTH);
        panelIzquierda.add(new JScrollPane(listaSensores), BorderLayout.CENTER);

        modeloAlertas = new DefaultListModel<>();
        listaAlertas = new JList<>(modeloAlertas);
        JPanel panelCentro = new JPanel(new BorderLayout(5, 5));
        panelCentro.setBorder(BorderFactory.createTitledBorder("Alertas configuradas"));
        panelCentro.add(new JScrollPane(listaAlertas), BorderLayout.CENTER);

        campoLimite = new JTextField();
        botonGuardar = new JButton("Guardar Alerta");
        botonGuardar.addActionListener(e -> guardarAlerta());

        JPanel panelDerecha = new JPanel(new GridLayout(3, 1, 5, 5));
        panelDerecha.setBorder(BorderFactory.createTitledBorder("Nueva alerta"));
        panelDerecha.add(new JLabel("Límite permitido:"));
        panelDerecha.add(campoLimite);
        panelDerecha.add(botonGuardar);

        JPanel panelContenido = new JPanel(new GridLayout(1, 3, 10, 10));
        panelContenido.add(panelIzquierda);
        panelContenido.add(panelCentro);
        panelContenido.add(panelDerecha);

        add(panelContenido, BorderLayout.CENTER);

        cargarSensores();
    }

    private void cargarSensores() {
        if (comboTipos == null || modeloSensores == null) return;

        try {
            modeloSensores.clear();
            List<String> sensores = sensorService.obtenerSensoresPorTipo((String) comboTipos.getSelectedItem());
            for (String s : sensores) modeloSensores.addElement(s);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar sensores: " + e.getMessage());
        }
    }

    private void cargarAlertasSensor() {
        if (listaSensores == null || modeloAlertas == null) return;

        modeloAlertas.clear();
        String seleccionado = listaSensores.getSelectedValue();
        if (seleccionado == null) return;

        int sensorId = Integer.parseInt(seleccionado.split(" ")[0]);
        try {
            List<Alerta> alertas = alertaService.obtenerAlertasPorSensor(sensorId);
            for (Alerta alerta : alertas) {
                modeloAlertas.addElement("Límite: " + alerta.valorActual + " (" + alerta.tipo + ")");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar alertas: " + e.getMessage());
        }
    }

    private void guardarAlerta() {
        if (listaSensores == null || campoLimite == null || comboTipos == null) return;

        String seleccionado = listaSensores.getSelectedValue();
        if (seleccionado == null || campoLimite.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un sensor y escribe un límite.");
            return;
        }

        try {
            int sensorId = Integer.parseInt(seleccionado.split(" ")[0]);
            double limite = Double.parseDouble(campoLimite.getText());
            String tipo = (String) comboTipos.getSelectedItem();
            String nombreAlerta = tipo + " - Alerta";
            Alerta alerta = new Alerta(sensorId, tipo, limite, nombreAlerta);
            alertaService.guardarAlerta(alerta);
            JOptionPane.showMessageDialog(this, "Alerta guardada correctamente.");
            campoLimite.setText("");
            cargarAlertasSensor();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar alerta: " + e.getMessage());
        }
    }
}
