package UI.GestionUsuario;

import GestionUsuario.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class PanelCambiarNombre extends JPanel {
    private JTextField nuevoNombreField;
    private String usuarioActual;
    private Connection conn;

    public PanelCambiarNombre(String usuarioActual, Connection conn) {
        this.usuarioActual = usuarioActual;
        this.conn = conn;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("Cambiar Nombre de Usuario");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);

        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Nombre actual:"), gbc);
        gbc.gridx = 1;
        add(new JLabel(usuarioActual), gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Nuevo nombre:"), gbc);
        gbc.gridx = 1;
        nuevoNombreField = new JTextField(15);
        add(nuevoNombreField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> cambiarNombre());
        add(btnActualizar, gbc);
    }

    private void cambiarNombre() {
        String nuevoNombre = nuevoNombreField.getText().trim();
        if (nuevoNombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nuevo nombre no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean actualizado = UsuarioService.actualizarNombreUsuario(conn, usuarioActual, nuevoNombre);
        if (actualizado) {
            JOptionPane.showMessageDialog(this, "Nombre actualizado correctamente.");
            usuarioActual = nuevoNombre;
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el nombre. ¿Ya existe ese nombre?", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
