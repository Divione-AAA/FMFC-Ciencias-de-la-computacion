package UI.GestionUsuario;

import GestionUsuario.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;

public class PanelCambiarContrasena extends JPanel {
    private JTextField campoNuevaContrasena;
    private JTextField campoConfirmarContrasena;
    private JLabel mensaje;
    private Connection conn;
    private String nombreUsuario;

    public PanelCambiarContrasena(Connection conn, String nombreUsuario) {
        this.conn = conn;
        this.nombreUsuario = nombreUsuario;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Cambiar Contraseña para: " + nombreUsuario);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setHorizontalAlignment(JLabel.CENTER);

        campoNuevaContrasena = new JPasswordField(15);
        campoConfirmarContrasena = new JPasswordField(15);
        JButton botonCambiar = new JButton("Actualizar Contraseña");
        mensaje = new JLabel("", JLabel.CENTER);
        mensaje.setForeground(Color.RED);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        add(new JLabel("Nueva Contraseña:"), gbc);

        gbc.gridx = 1;
        add(campoNuevaContrasena, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Confirmar Contraseña:"), gbc);

        gbc.gridx = 1;
        add(campoConfirmarContrasena, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(botonCambiar, gbc);

        gbc.gridy++;
        add(mensaje, gbc);

        botonCambiar.addActionListener(this::cambiarContrasena);
    }

    private void cambiarContrasena(ActionEvent e) {
        String nueva = campoNuevaContrasena.getText();
        String confirmar = campoConfirmarContrasena.getText();

        if (nueva.isEmpty() || confirmar.isEmpty()) {
            mensaje.setText("Todos los campos son obligatorios.");
            return;
        }

        if (!nueva.equals(confirmar)) {
            mensaje.setText("Las contraseñas no coinciden.");
            return;
        }

        boolean exito = UsuarioService.actualizarContrasena(conn, nombreUsuario, nueva);
        if (exito) {
            mensaje.setForeground(Color.GREEN);
            mensaje.setText("Contraseña actualizada correctamente.");
        } else {
            mensaje.setForeground(Color.RED);
            mensaje.setText("Error al actualizar la contraseña.");
        }
    }
}
