package UI.GestionUsuario;

import GestionUsuario.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class PanelGestionUsuario extends JPanel {
    private JList<String> listaUsuarios;
    private DefaultListModel<String> modeloLista;
    private JPanel panelDerecho;
    private String usuarioActual;
    private String rolActual;

    public PanelGestionUsuario(String usuario, String rol) throws SQLException {
        this.usuarioActual = usuario;
        this.rolActual = rol;
        setLayout(new BorderLayout());

        if ("admin".equalsIgnoreCase(rolActual)) {
            construirPanelAdmin();
        }
    }

    private void construirPanelAdmin() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
        modeloLista = new DefaultListModel<>();
        listaUsuarios = new JList<>(modeloLista);
        cargarUsuarios();

        listaUsuarios.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String usuarioSeleccionado = listaUsuarios.getSelectedValue();
                if (usuarioSeleccionado != null) {
                    try {
                        mostrarOpcionesUsuario(usuarioSeleccionado);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        JScrollPane scroll = new JScrollPane(listaUsuarios);
        scroll.setPreferredSize(new Dimension(200, 0));

        JButton btnAnadir = new JButton("Añadir usuario");
        btnAnadir.addActionListener(e -> {
            panelDerecho.removeAll();
            panelDerecho.add(new PanelAddUsers(conn), BorderLayout.CENTER);
            panelDerecho.revalidate();
            panelDerecho.repaint();
        });

        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.add(scroll, BorderLayout.CENTER);
        panelIzquierdo.add(btnAnadir, BorderLayout.SOUTH);

        panelDerecho = new JPanel(new BorderLayout());

        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
    }

    private JButton crearBotonConIcono(String texto, String rutaIcono) {
        ImageIcon iconoOriginal = new ImageIcon(rutaIcono);
        Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        ImageIcon iconoFinal = new ImageIcon(imagenRedimensionada);

        JButton boton = new JButton("<html><center>" + texto + "</center></html>", iconoFinal);
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setPreferredSize(new Dimension(120, 120));
        boton.setFocusPainted(false);
        boton.setBackground(UIManager.getColor("Button.background"));
        boton.setForeground(UIManager.getColor("Button.foreground"));
        boton.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 1));

        // 🟦 Estilo Main.FlatLaf: bordes redondeados
        boton.putClientProperty("JComponent.roundRect", true);
        boton.putClientProperty("Component.arc", 20); // más redondeado

        return boton;
    }


    private void mostrarOpcionesUsuario(String usuario) throws SQLException {
        panelDerecho.removeAll();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");

        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel bienvenida = new JLabel("Opciones para: " + usuario, JLabel.CENTER);
        bienvenida.setFont(new Font("SansSerif", Font.BOLD, 18));
        panelDerecho.add(bienvenida, BorderLayout.NORTH);

        // Crear botones con íconos y texto debajo
        JButton cambiarNombre = crearBotonConIcono("Cambiar nombre", "src/resources/user.png");
        cambiarNombre.addActionListener(e -> {
            panelDerecho.removeAll();
            panelDerecho.add(new PanelCambiarNombre(usuario, conn), BorderLayout.CENTER);
            panelDerecho.revalidate();
            panelDerecho.repaint();
        });

        JButton cambiarContrasena = crearBotonConIcono("Cambiar contraseña", "src/resources/key.png");
        cambiarContrasena.addActionListener(e -> {
            panelDerecho.removeAll();
            panelDerecho.add(new PanelCambiarContrasena(conn, usuario), BorderLayout.CENTER);
            panelDerecho.revalidate();
            panelDerecho.repaint();
        });

        JButton eliminarUsuario = crearBotonConIcono("Eliminar usuario", "src/resources/delete.png");
        eliminarUsuario.addActionListener(e -> {
            panelDerecho.removeAll();
            panelDerecho.add(new PanelDelUser(conn), BorderLayout.CENTER);
            panelDerecho.revalidate();
            panelDerecho.repaint();
        });

        // Añadir botones al panel central
        panelCentral.add(cambiarNombre, gbc);
        gbc.gridx++;
        panelCentral.add(cambiarContrasena, gbc);
        gbc.gridx++;
        panelCentral.add(eliminarUsuario, gbc);

        panelDerecho.add(panelCentral, BorderLayout.CENTER);
        panelDerecho.revalidate();
        panelDerecho.repaint();
    }


    private void cargarUsuarios() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");

        try {
            List<String> usuarios = UsuarioService.obtenerTodos(conn);
            modeloLista.clear();
            for (String user : usuarios) {
                if (!user.equalsIgnoreCase(usuarioActual)) {
                    modeloLista.addElement(user);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + e.getMessage());
        }
    }
}
