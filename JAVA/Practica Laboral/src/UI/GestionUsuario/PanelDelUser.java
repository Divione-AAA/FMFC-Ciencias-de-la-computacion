package UI.GestionUsuario;

import GestionUsuario.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;

public class PanelDelUser extends JPanel {
    private JTextField campoUsuario;
    private JLabel mensaje;
    private Connection conn;

    public PanelDelUser(Connection conn) {
        this.conn = conn;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Eliminar Usuario");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setHorizontalAlignment(JLabel.CENTER);

        campoUsuario = new JTextField(15);
        JButton botonEliminar = new JButton("Eliminar Usuario");
        mensaje = new JLabel("", JLabel.CENTER);
        mensaje.setForeground(Color.RED);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        add(new JLabel("Nombre del usuario a eliminar:"), gbc);

        gbc.gridx = 1;
        add(campoUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(botonEliminar, gbc);

        gbc.gridy++;
        add(mensaje, gbc);

        botonEliminar.addActionListener(this::eliminarUsuario);
    }

    private void eliminarUsuario(ActionEvent e) {
        String usuario = campoUsuario.getText().trim();

        if (usuario.isEmpty()) {
            mensaje.setText("Debe ingresar el nombre del usuario.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar al usuario \"" + usuario + "\"?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean exito = UsuarioService.eliminarUsuario(conn, usuario);
            if (exito) {
                mensaje.setForeground(Color.GREEN);
                mensaje.setText("Usuario eliminado exitosamente.");
            } else {
                mensaje.setForeground(Color.RED);
                mensaje.setText("No se pudo eliminar el usuario.");
            }
        }
    }
}
