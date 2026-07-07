package UI.Profesor;

import Modelos.Profesor;
import Servicios.ProfesorService;

import javax.swing.*;
import java.awt.*;

public class PanelEliminarProfesor extends JPanel {

    private final ProfesorService profesorService;
    private JTextField txtBuscarCI;
    private JButton btnBuscar, btnEliminar;

    private JLabel lblNombre, lblDetalles;
    private String ciSeleccionado = "";

    public PanelEliminarProfesor() {
        this.profesorService = new ProfesorService();
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Panel Superior Búsqueda ---
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Remoción de Docente"));
        panelBusqueda.add(new JLabel("CI del Profesor:"));
        txtBuscarCI = new JTextField(12);
        panelBusqueda.add(txtBuscarCI);
        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);
        add(panelBusqueda, BorderLayout.NORTH);

        // --- Panel Central Confirmación ---
        JPanel panelInfo = new JPanel(new GridLayout(2, 1, 10, 10));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Ficha del Registro Seleccionado"));

        lblNombre = new JLabel("Nombre: (Efectúe una consulta previa)");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblDetalles = new JLabel("Cargo / Categoría: ");

        panelInfo.add(lblNombre);
        panelInfo.add(lblDetalles);
        add(panelInfo, BorderLayout.CENTER);

        // --- Panel Inferior Botón Acción ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnEliminar = new JButton("Baja Definitiva");
        btnEliminar.setBackground(new Color(220, 53, 69));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setEnabled(false);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscarProfesor());
        btnEliminar.addActionListener(e -> eliminarProfesor());
    }

    private void buscarProfesor() {
        String ci = txtBuscarCI.getText().trim();
        if (ci.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserte el CI correspondiente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Profesor p = profesorService.buscarPorCI(ci);
            if (p != null) {
                ciSeleccionado = p.getCi();
                lblNombre.setText("Nombre: " + p.getNombreCompleto());
                lblDetalles.setText("Cargo: " + p.getCargo() + " | Categoría: " + p.getCategoriaDocente());
                btnEliminar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "El docente no figura en el sistema.", "No Encontrado", JOptionPane.INFORMATION_MESSAGE);
                resetFicha();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error de consulta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarProfesor() {
        if (ciSeleccionado.isEmpty()) return;

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Confirmar la eliminación irrevocable del docente en la base de datos?",
                "Alerta del Sistema",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                profesorService.eliminar(ciSeleccionado);
                JOptionPane.showMessageDialog(this, "El registro se removió exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                resetFicha();
                txtBuscarCI.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error de remoción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetFicha() {
        ciSeleccionado = "";
        lblNombre.setText("Nombre: (Efectúe una consulta previa)");
        lblDetalles.setText("Cargo / Categoría: ");
        btnEliminar.setEnabled(false);
    }
}