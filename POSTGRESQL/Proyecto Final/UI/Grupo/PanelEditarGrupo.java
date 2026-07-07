package UI.Grupo;

import Modelos.Grupo;
import Servicios.GrupoService;

import javax.swing.*;
import java.awt.*;

public class PanelEditarGrupo extends JPanel {

    private final GrupoService grupoService;

    // Componentes de búsqueda
    private JTextField txtBuscarId;
    private JButton btnBuscar;

    // Componentes del formulario
    private JTextField txtNombre;
    private JTextField txtEscuelaId;
    private JButton btnGuardar;

    private int idGrupoSeleccionado = -1;

    public PanelEditarGrupo() {
        this.grupoService = new GrupoService();
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Panel Superior: Búsqueda ---
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Buscar Grupo a Editar"));

        panelBusqueda.add(new JLabel("ID / Código del Grupo:"));
        txtBuscarId = new JTextField(10);
        panelBusqueda.add(txtBuscarId);

        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);

        add(panelBusqueda, BorderLayout.NORTH);

        // --- Panel Central: Formulario ---
        JPanel panelFormulario = new JPanel(new GridLayout(2, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Grupo"));

        txtNombre = new JTextField();
        txtEscuelaId = new JTextField();

        panelFormulario.add(new JLabel("Nombre del Grupo (*):"));
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("ID de la Escuela (*):"));
        panelFormulario.add(txtEscuelaId);

        setFormularioHabilitado(false);
        add(panelFormulario, BorderLayout.CENTER);

        // --- Panel Inferior: Botón Guardar ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setEnabled(false);
        panelBotones.add(btnGuardar);

        add(panelBotones, BorderLayout.SOUTH);

        // --- Eventos ---
        btnBuscar.addActionListener(e -> buscarGrupo());
        btnGuardar.addActionListener(e -> guardarCambios());
    }

    private void setFormularioHabilitado(boolean habilitado) {
        txtNombre.setEnabled(habilitado);
        txtEscuelaId.setEnabled(habilitado);
    }

    private void buscarGrupo() {
        String idText = txtBuscarId.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del grupo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idGrupo = Integer.parseInt(idText);
            Grupo grupo = grupoService.buscarGrupo(idGrupo);

            if (grupo != null) {
                idGrupoSeleccionado = grupo.getIdGrupo();
                txtNombre.setText(grupo.getNombre());
                txtEscuelaId.setText(String.valueOf(grupo.getEscuelaId()));

                setFormularioHabilitado(true);
                btnGuardar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún grupo con ese ID.", "No Encontrado", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarCambios() {
        try {
            int escuelaId = Integer.parseInt(txtEscuelaId.getText().trim());

            // Instanciamos el modelo manteniendo el ID original y actualizando sus propiedades
            Grupo grupoActualizado = new Grupo(
                    idGrupoSeleccionado,
                    String.valueOf(idGrupoSeleccionado),
                    txtNombre.getText().trim(),
                    escuelaId
            );

            grupoService.actualizarGrupo(grupoActualizado);
            JOptionPane.showMessageDialog(this, "Grupo actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            limpiarFormulario();
            txtBuscarId.setText("");
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "El ID de la Escuela debe ser un número entero.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        idGrupoSeleccionado = -1;
        txtNombre.setText("");
        txtEscuelaId.setText("");
        setFormularioHabilitado(false);
        btnGuardar.setEnabled(false);
    }
}