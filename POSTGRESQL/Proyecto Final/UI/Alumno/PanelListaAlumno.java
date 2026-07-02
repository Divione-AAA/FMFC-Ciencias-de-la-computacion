package UI.Alumno;

import DAO.AlumnoDAO;
import Modelos.Alumno;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class PanelListaAlumno extends JPanel {

    private final DefaultListModel<String> modelo = new DefaultListModel<>();

    public PanelListaAlumno() {
        setLayout(new BorderLayout(10, 10));
        JLabel titulo = new JLabel("Listado de alumnos", SwingConstants.CENTER);
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
            List<Alumno> alumnos = new AlumnoDAO().obtenerTodos();
            if (alumnos.isEmpty()) {
                modelo.addElement("No hay alumnos registrados.");
                return;
            }
            for (Alumno alumno : alumnos) {
                modelo.addElement(alumno.getCi() + " - " + alumno.getNombre1() + " " + alumno.getApellido1());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error cargando alumnos: " + ex.getMessage());
        }
    }
}