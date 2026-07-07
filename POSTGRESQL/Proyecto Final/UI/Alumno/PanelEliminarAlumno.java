package UI.Alumno;

import Modelos.Alumno;
import Servicios.AlumnoService;

import javax.swing.*;
import java.awt.*;

public class PanelEliminarAlumno extends JPanel {

    private final AlumnoService alumnoService;
    private JTextField txtBuscarCI;
    private JButton btnBuscar;
    private JButton btnEliminar;

    // Etiquetas para mostrar información de confirmación
    private JLabel lblNombreCompleto;
    private JLabel lblGradoGrupo;
    private String ciSeleccionado = "";

    public PanelEliminarAlumno() {
        this.alumnoService = new AlumnoService();
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Panel Superior: Búsqueda ---
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Buscar Alumno a Eliminar"));

        panelBusqueda.add(new JLabel("CI del Alumno:"));
        txtBuscarCI = new JTextField(15);
        panelBusqueda.add(txtBuscarCI);

        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);

        add(panelBusqueda, BorderLayout.NORTH);

        // --- Panel Central: Vista previa del Alumno a eliminar ---
        JPanel panelInfo = new JPanel(new GridLayout(3, 1, 10, 10));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Información de Confirmación"));

        lblNombreCompleto = new JLabel("Nombre: (Busque un alumno primero)");
        lblNombreCompleto.setFont(new Font("Arial", Font.BOLD, 14));

        lblGradoGrupo = new JLabel("Grado / Grupo: ");

        panelInfo.add(lblNombreCompleto);
        panelInfo.add(lblGradoGrupo);

        add(panelInfo, BorderLayout.CENTER);

        // --- Panel Inferior: Botón Eliminar ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnEliminar = new JButton("Eliminar Alumno");
        btnEliminar.setBackground(new Color(220, 53, 69)); // Color rojo
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setEnabled(false);

        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        // --- Eventos ---
        btnBuscar.addActionListener(e -> buscarAlumno());
        btnEliminar.addActionListener(e -> eliminarAlumno());
    }

    private void buscarAlumno() {
        String ci = txtBuscarCI.getText().trim();
        if (ci.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un CI.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Alumno alumno = alumnoService.buscarPorCI(ci);
            if (alumno != null) {
                ciSeleccionado = alumno.getCi();
                String nombreCompleto = alumno.getNombre1() + " " + alumno.getNombre2() + " " +
                        alumno.getApellido1() + " " + alumno.getApellido2();

                lblNombreCompleto.setText("Nombre: " + nombreCompleto.replaceAll("\\s+", " "));
                lblGradoGrupo.setText("Grado: " + alumno.getGrado() + " | Grupo ID: " + alumno.getCodigoGrupo());

                btnEliminar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún alumno con ese CI.", "No Encontrado", JOptionPane.INFORMATION_MESSAGE);
                resetInfo();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarAlumno() {
        if (ciSeleccionado.isEmpty()) return;

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está completamente seguro de que desea eliminar a este alumno?\nEsta acción no se puede deshacer.",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                alumnoService.eliminar(ciSeleccionado);
                JOptionPane.showMessageDialog(this, "Alumno eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                resetInfo();
                txtBuscarCI.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetInfo() {
        ciSeleccionado = "";
        lblNombreCompleto.setText("Nombre: (Busque un alumno primero)");
        lblGradoGrupo.setText("Grado / Grupo: ");
        btnEliminar.setEnabled(false);
    }
}