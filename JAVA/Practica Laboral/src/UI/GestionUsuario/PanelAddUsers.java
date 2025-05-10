package UI.GestionUsuario;

import GestionUsuario.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class PanelAddUsers extends JPanel {
    private JTextField campoNombre;
    private JPasswordField campoContrasena;
    private JComboBox<String> comboRol;
    private Connection conn;

    public PanelAddUsers(Connection conn) {
        this.conn = conn;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("Añadir Nuevo Usuario");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);

        gbc.gridwidth = 1;

        // Nombre de usuario
        gbc.gridy++;
        add(new JLabel("Nombre de usuario:"), gbc);
        gbc.gridx = 1;
        campoNombre = new JTextField(15);
        add(campoNombre, gbc);

        // Contraseña
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        campoContrasena = new JPasswordField(15);
        add(campoContrasena, gbc);

        // Rol
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Rol:"), gbc);
        gbc.gridx = 1;
        comboRol = new JComboBox<>(new String[]{"admin", "invitado"});
        add(comboRol, gbc);

        // Botón para crear usuario
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton btnCrear = new JButton("Crear Usuario");
        btnCrear.addActionListener(e -> crearUsuario());
        add(btnCrear, gbc);
    }

    private void crearUsuario() {
        String nombre = campoNombre.getText().trim();
        String contrasena = new String(campoContrasena.getPassword()).trim();
        String rol = (String) comboRol.getSelectedItem();

        if (nombre.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean creado = UsuarioService.crearUsuario(conn, nombre, contrasena, rol);
        if (creado) {
            JOptionPane.showMessageDialog(this, "Usuario creado con éxito.");
            campoNombre.setText("");
            campoContrasena.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo crear el usuario. Verifique si el nombre ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
