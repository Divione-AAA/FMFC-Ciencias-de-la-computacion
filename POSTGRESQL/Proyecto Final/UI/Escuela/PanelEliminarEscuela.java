package UI.Escuela;

import Modelos.Escuela;
import Servicios.EscuelaService;

import javax.swing.*;
import java.awt.*;

public class PanelEliminarEscuela extends JPanel {

    private final EscuelaService escuelaService;

    private JTextField txtBuscarCodigo;
    private JButton btnBuscar;
    private JButton btnEliminar;

    // Componentes para mostrar la vista previa del registro a eliminar
    private JLabel lblNombreEscuela;
    private JLabel lblDireccionEscuela;
    private int codigoSeleccionado = -1;

    public PanelEliminarEscuela() {
        this.escuelaService = new EscuelaService();
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Panel Superior: Búsqueda ---
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Buscar Escuela a Eliminar"));

        panelBusqueda.add(new JLabel("Código de Escuela:"));
        txtBuscarCodigo = new JTextField(10);
        panelBusqueda.add(txtBuscarCodigo);

        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);

        add(panelBusqueda, BorderLayout.NORTH);

        // --- Panel Central: Información de confirmación ---
        JPanel panelInfo = new JPanel(new GridLayout(2, 1, 10, 10));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Información de la Escuela Seleccionada"));

        lblNombreEscuela = new JLabel("Nombre: (Busque una escuela primero)");
        lblNombreEscuela.setFont(new Font("Arial", Font.BOLD, 14));

        lblDireccionEscuela = new JLabel("Dirección: ");

        panelInfo.add(lblNombreEscuela);
        panelInfo.add(lblDireccionEscuela);

        add(panelInfo, BorderLayout.CENTER);

        // --- Panel Inferior: Botón Eliminar ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnEliminar = new JButton("Eliminar Escuela");
        btnEliminar.setBackground(new Color(220, 53, 69)); // Fondo Rojo
        btnEliminar.setForeground(Color.WHITE);            // Texto Blanco
        btnEliminar.setEnabled(false);

        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        // --- Eventos ---
        btnBuscar.addActionListener(e -> buscarEscuela());
        btnEliminar.addActionListener(e -> eliminarEscuela());
    }

    private void buscarEscuela() {
        String codigoText = txtBuscarCodigo.getText().trim();
        if (codigoText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un código.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoText);
            Escuela escuela = escuelaService.buscarEscuela(codigo);

            if (escuela != null) {
                codigoSeleccionado = escuela.getCodigoEscuela();
                lblNombreEscuela.setText("Nombre: " + escuela.getNombre());
                lblDireccionEscuela.setText("Dirección: " + escuela.getDireccion() + " (" + escuela.getTipo() + ")");
                btnEliminar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna escuela con ese código.", "No Encontrado", JOptionPane.INFORMATION_MESSAGE);
                resetInfo();
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "El código debe ser un número entero válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEscuela() {
        if (codigoSeleccionado == -1) return;

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea eliminar permanentemente esta escuela?\nEsta acción no se puede revertir.",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                escuelaService.eliminarEscuela(codigoSeleccionado);
                JOptionPane.showMessageDialog(this, "Escuela eliminada de manera exitosa.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                resetInfo();
                txtBuscarCodigo.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetInfo() {
        codigoSeleccionado = -1;
        lblNombreEscuela.setText("Nombre: (Busque una escuela primero)");
        lblDireccionEscuela.setText("Dirección: ");
        btnEliminar.setEnabled(false);
    }
}