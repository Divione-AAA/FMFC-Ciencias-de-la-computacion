package UI.Grupo;

import DAO.GrupoDAO;
import Modelos.Grupo;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class PanelListaGrupo extends JPanel {

    private final DefaultListModel<String> modelo = new DefaultListModel<>();

    public PanelListaGrupo() {
        setLayout(new BorderLayout(10, 10));
        JLabel titulo = new JLabel("Listado de grupos", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JList<String> lista = new JList<>(modelo);
        add(new JScrollPane(lista), BorderLayout.CENTER);

        JButton refrescar = new JButton("Actualizar");
        refrescar.addActionListener(e -> cargar());
        JPanel inferior = new JPanel();
        inferior.add(refrescar);
        add(inferior, BorderLayout.SOUTH);

        cargar();
    }

    private void cargar() {
        try {
            modelo.clear();
            List<Grupo> grupos = new GrupoDAO().obtenerTodos();
            if (grupos.isEmpty()) {
                modelo.addElement("No hay grupos registrados.");
                return;
            }
            for (Grupo grupo : grupos) {
                modelo.addElement(grupo.getCodigoGrupo() + " - " + grupo.getNombre());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error cargando grupos: " + ex.getMessage());
        }
    }
}