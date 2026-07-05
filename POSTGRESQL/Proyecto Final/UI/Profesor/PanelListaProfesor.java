package UI.Profesor;

import DAO.ProfesorDAO;
import Modelos.Profesor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelListaProfesor extends JPanel {

    private final DefaultListModel<String> modelo =
            new DefaultListModel<>();

    public PanelListaProfesor() {

        setLayout(
                new BorderLayout(10, 10)
        );

        JLabel titulo =
                new JLabel(
                        "Listado de profesores",
                        SwingConstants.CENTER
                );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        add(
                titulo,
                BorderLayout.NORTH
        );

        JList<String> lista =
                new JList<>(modelo);

        add(
                new JScrollPane(lista),
                BorderLayout.CENTER
        );

        JButton refrescar =
                new JButton("Actualizar");

        refrescar.addActionListener(
                e -> cargar()
        );

        JPanel inferior =
                new JPanel();

        inferior.add(
                refrescar
        );

        add(
                inferior,
                BorderLayout.SOUTH
        );

        cargar();

    }

    private void cargar() {

        try {

            modelo.clear();

            List<Profesor> profesores =
                    new ProfesorDAO().obtenerTodos();

            if (profesores.isEmpty()) {

                modelo.addElement(
                        "No hay profesores registrados."
                );

                return;

            }

            for (Profesor profesor : profesores) {

                modelo.addElement(

                        profesor.getCi()
                                + " - "
                                + profesor.getNombreCompleto()
                                + " | "
                                + profesor.getCategoriaDocente()

                );

            }

        }

        catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando profesores:\n"
                            + ex.getMessage()
            );

        }

    }

}