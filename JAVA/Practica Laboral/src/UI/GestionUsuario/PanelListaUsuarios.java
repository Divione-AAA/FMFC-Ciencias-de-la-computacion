package UI.GestionUsuario;

import GestionUsuario.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class PanelListaUsuarios extends JPanel {
    private JList<String> listaUsuarios;
    private DefaultListModel<String> modeloLista;
    private Connection conn;

    public PanelListaUsuarios(Connection conn) {
        this.conn = conn;
        setLayout(new BorderLayout());
        modeloLista = new DefaultListModel<>();

        listaUsuarios = new JList<>(modeloLista);
        listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        cargarUsuarios();

        JScrollPane scrollPane = new JScrollPane(listaUsuarios);
        add(scrollPane, BorderLayout.CENTER);
        setBorder(BorderFactory.createTitledBorder("Usuarios"));
    }

    private void cargarUsuarios() {
        List<String> usuarios = UsuarioService.obtenerNombresUsuarios(conn);
        modeloLista.clear();
        for (String usuario : usuarios) {
            modeloLista.addElement(usuario);
        }
    }

    public JList<String> getListaUsuarios() {
        return listaUsuarios;
    }
}
