package UI.GestionUsuario;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import GestionUsuario.Login; // Importamos la clase Login


public class LoginUI extends JFrame {
    private JTextField usuarioField;
    private JPasswordField contrasenaField;

    public LoginUI() {
        // Configuración de la ventana
        setTitle("Inicio de sesión");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250); // Ajustamos el tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setResizable(false); // Hacer que la ventana no se pueda redimensionar
        setLayout(new BorderLayout());

        // Crear un panel para los componentes de la UI
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel titulo = new JLabel("Iniciar sesión");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(titulo, gbc);

        // Usuario
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 10);
        panel.add(new JLabel("Usuario:"), gbc);

        usuarioField = new JTextField();
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(usuarioField, gbc);

        // Contraseña
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 10);
        panel.add(new JLabel("Contraseña:"), gbc);

        contrasenaField = new JPasswordField();
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(contrasenaField, gbc);

        // Botón de login
        JButton loginButton = new JButton("Ingresar");
        loginButton.addActionListener(e -> intentarLogin());
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(loginButton, gbc);

        // Agregar el panel al frame
        add(panel, BorderLayout.CENTER);
    }

    private void intentarLogin() {
        String usuario = usuarioField.getText().trim();
        String clave = new String(contrasenaField.getPassword());

        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db")) {
            // Llamamos al método de Login para validar las credenciales
            boolean esValido = Login.login(conn, usuario, clave);
            String rol = Login.obtenerRol(conn,usuario,clave);

            if (esValido) {
                // Si el login es exitoso
                JOptionPane.showMessageDialog(this, "¡Bienvenido, " + usuario + "!");
                this.dispose(); // Cierra la ventana de login
                // Abre el panel principal (Dashboard)
                new DashboardFrame(usuario,rol,conn).setVisible(true);
            } else {
                // Si el login falla
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error de base de datos: " + e.getMessage());
        }
    }

    // Punto de entrada para ejecutar la interfaz
    public static void main(String[] args) {
        // Inicializa el look-and-feel moderno (FlatLaf)
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Crea la ventana de login
        SwingUtilities.invokeLater(() -> new LoginUI().setVisible(true));
    }
}
