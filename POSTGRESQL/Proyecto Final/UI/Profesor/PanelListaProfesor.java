package UI.Profesor;

import DAO.ProfesorDAO;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class PanelListaProfesor extends JPanel {

    private final DefaultListModel<String> modelo = new DefaultListModel<>();

    public PanelListaProfesor() {
        setLayout(new BorderLayout(10, 10));
        JLabel titulo = new JLabel("Listado de profesores", SwingConstants.CENTER);
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
            List<String> profesores = new ProfesorDAO().obtenerTodos();
            if (profesores.isEmpty()) {
                modelo.addElement("No hay profesores registrados.");
                return;
            }
            for (String nombre : profesores) {
                modelo.addElement(nombre);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error cargando profesores: " + ex.getMessage());
        }
    }
}