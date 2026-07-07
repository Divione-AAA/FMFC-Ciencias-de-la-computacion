package UI.Grupo;

import Modelos.Grupo;
import Servicios.GrupoService;

import javax.swing.*;
import java.awt.*;

public class PanelEliminarGrupo extends JPanel {

    private final GrupoService grupoService;

    private JTextField txtBuscarId;
    private JButton btnBuscar;
    private JButton btnEliminar;

    private JLabel lblDetallesGrupo;
    private int idGrupoSeleccionado = -1;

    public PanelEliminarGrupo() {
        this.grupoService = new GrupoService();
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Panel Superior: Búsqueda ---
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Buscar Grupo a Eliminar"));

        panelBusqueda.add(new JLabel("ID / Código del Grupo:"));
        txtBuscarId = new JTextField(10);
        panelBusqueda.add(txtBuscarId);

        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);

        add(panelBusqueda, BorderLayout.NORTH);

        // --- Panel Central: Vista previa ---
        JPanel panelInfo = new JPanel(new GridLayout(1, 1, 10, 10));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Información de Confirmación"));

        lblDetallesGrupo = new JLabel("Grupo: (Realice una búsqueda para cargar los datos)");
        lblDetallesGrupo.setFont(new Font("Arial", Font.BOLD, 14));
        panelInfo.add(lblDetallesGrupo);

        add(panelInfo, BorderLayout.CENTER);

        // --- Panel Inferior: Botón Eliminar ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnEliminar = new JButton("Eliminar Grupo");
        btnEliminar.setBackground(new Color(220, 53, 69)); // Color rojo de advertencia
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setEnabled(false);

        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        // --- Eventos ---
        btnBuscar.addActionListener(e -> buscarGrupo());
        btnEliminar.addActionListener(e -> eliminarGrupo());
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
                lblDetallesGrupo.setText("Grupo: " + grupo.getNombre() + " (Pertenece a Escuela ID: " + grupo.getEscuelaId() + ")");
                btnEliminar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún grupo con ese ID.", "No Encontrado", JOptionPane.INFORMATION_MESSAGE);
                resetInfo();
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarGrupo() {
        if (idGrupoSeleccionado == -1) return;

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea eliminar este grupo?\nNota: Asegúrese de que no tenga alumnos asignados para evitar errores de restricción.",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                grupoService.eliminarGrupo(idGrupoSeleccionado);
                JOptionPane.showMessageDialog(this, "Grupo eliminado de manera exitosa.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                resetInfo();
                txtBuscarId.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetInfo() {
        idGrupoSeleccionado = -1;
        lblDetallesGrupo.setText("Grupo: (Realice una búsqueda para cargar los datos)");
        btnEliminar.setEnabled(false);
    }
}