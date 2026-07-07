package UI.Login;

import Database.ConnectionDB;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class DialogoLoginDB extends JDialog {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnConectar;
    private JButton btnCancelar;
    private boolean conectadoExitosamente = false;

    public DialogoLoginDB() {
        setTitle("Acceso a Base de Datos");
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        // Formulario
        JPanel panelCampos = new JPanel(new GridLayout(2, 2, 5, 10));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        txtUsuario = new JTextField("postgres", 15); // Sugerencia por defecto
        txtPassword = new JPasswordField(15);

        panelCampos.add(new JLabel("Usuario:"));
        panelCampos.add(txtUsuario);
        panelCampos.add(new JLabel("Contraseña:"));
        panelCampos.add(txtPassword);

        add(panelCampos, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnConectar = new JButton("Conectar");
        btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnConectar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        btnConectar.addActionListener(e -> intentarConexion());
        btnCancelar.addActionListener(e -> dispose());

        pack();
        setLocationRelativeTo(null); // Centrar en pantalla
    }

    private void intentarConexion() {
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword());

        try (Connection conn = ConnectionDB.testConnection(usuario, password)) {
            // Si no lanza excepción, la conexión fue exitosa
            ConnectionDB.setCredentials(usuario, password);
            conectadoExitosamente = true;
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error de conexión: Credenciales incorrectas.\n" + ex.getMessage(),
                    "Fallo de Autenticación",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isConectadoExitosamente() {
        return conectadoExitosamente;
    }
}