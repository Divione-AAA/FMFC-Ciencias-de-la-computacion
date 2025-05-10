package UI.GestionUsuario;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class PanelUsuarios extends JPanel {
    private String rol;
    private Connection conn;

    public PanelUsuarios(String titulo, String rol, Connection conn,String usuario) throws SQLException {
        this.rol = rol;
        this.conn = conn;
        setLayout(new BorderLayout());

        if (!rol.equalsIgnoreCase("admin")) {
            mostrarAccesoDenegado();
        } else {
            mostrarPanelAdministrador(titulo,rol,usuario);
        }
    }

    private void mostrarAccesoDenegado() {
        setLayout(new GridBagLayout());

        // Panel de contenido centrado
        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);

        // Etiqueta del mensaje
        JLabel label = new JLabel("Se requieren permisos de administrador.");
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Carga y redimensiona el icono
        ImageIcon originalIcon = new ImageIcon("src/resources/lock.png");
        Image img = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);

        JLabel icono = new JLabel(resizedIcon);
        icono.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Añadir al panel contenido
        contenido.add(icono);
        contenido.add(label);

        // Añadir al panel principal
        add(contenido);
    }

    private void mostrarPanelAdministrador(String titulo,String rol, String usuario) throws SQLException {
        // Panel izquierdo: lista de usuarios
        JPanel panelLista = new PanelListaUsuarios(conn);
        panelLista.setPreferredSize(new Dimension(250, getHeight()));

        // Panel derecho: detalles del usuario seleccionado
        JPanel panelDetalle = new PanelGestionUsuario(usuario,rol);

        JPanel panelIzquierda = new JPanel(new BorderLayout());
        panelIzquierda.add(panelLista, BorderLayout.CENTER);

        add(panelIzquierda, BorderLayout.WEST);
        add(panelDetalle, BorderLayout.CENTER);
    }
}
