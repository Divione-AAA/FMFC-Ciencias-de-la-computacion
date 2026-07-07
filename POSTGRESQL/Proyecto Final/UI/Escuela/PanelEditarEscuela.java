package UI.Escuela;

import Modelos.Escuela;
import Servicios.EscuelaService;

import javax.swing.*;
import java.awt.*;

public class PanelEditarEscuela extends JPanel {

    private final EscuelaService escuelaService;

    // Componentes de búsqueda
    private JTextField txtBuscarCodigo;
    private JButton btnBuscar;

    // Componentes del formulario de edición
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtTipo;
    private JButton btnGuardar;

    private int codigoEscuelaSeleccionada;

    public PanelEditarEscuela() {
        this.escuelaService = new EscuelaService();
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Panel Superior: Búsqueda ---
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Buscar Escuela a Editar"));

        panelBusqueda.add(new JLabel("Código de Escuela:"));
        txtBuscarCodigo = new JTextField(10);
        panelBusqueda.add(txtBuscarCodigo);

        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);

        add(panelBusqueda, BorderLayout.NORTH);

        // --- Panel Central: Formulario ---
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos de la Escuela"));

        txtNombre = new JTextField();
        txtDireccion = new JTextField();
        txtTelefono = new JTextField();
        txtTipo = new JTextField();

        panelFormulario.add(new JLabel("Nombre (*):"));
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Dirección (*):"));
        panelFormulario.add(txtDireccion);

        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("Tipo:"));
        panelFormulario.add(txtTipo);

        // Inicialmente deshabilitado
        setFormularioHabilitado(false);

        add(panelFormulario, BorderLayout.CENTER);

        // --- Panel Inferior: Botón Guardar ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setEnabled(false);
        panelBotones.add(btnGuardar);

        add(panelBotones, BorderLayout.SOUTH);

        // --- Eventos ---
        btnBuscar.addActionListener(e -> buscarEscuela());
        btnGuardar.addActionListener(e -> guardarCambios());
    }

    private void setFormularioHabilitado(boolean habilitado) {
        txtNombre.setEnabled(habilitado);
        txtDireccion.setEnabled(habilitado);
        txtTelefono.setEnabled(habilitado);
        txtTipo.setEnabled(habilitado);
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
                codigoEscuelaSeleccionada = escuela.getCodigoEscuela();
                txtNombre.setText(escuela.getNombre());
                txtDireccion.setText(escuela.getDireccion());
                txtTelefono.setText(escuela.getTelefono());
                txtTipo.setText(escuela.getTipo());

                setFormularioHabilitado(true);
                btnGuardar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna escuela con ese código.", "No Encontrado", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "El código debe ser un número entero válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarCambios() {
        try {
            Escuela escuelaActualizada = new Escuela(
                    codigoEscuelaSeleccionada,
                    txtNombre.getText().trim(),
                    txtDireccion.getText().trim(),
                    txtTelefono.getText().trim(),
                    txtTipo.getText().trim()
            );

            escuelaService.actualizarEscuela(escuelaActualizada);
            JOptionPane.showMessageDialog(this, "Escuela actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            limpiarFormulario();
            txtBuscarCodigo.setText("");
        } catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(this, iae.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtTipo.setText("");
        setFormularioHabilitado(false);
        btnGuardar.setEnabled(false);
    }
}