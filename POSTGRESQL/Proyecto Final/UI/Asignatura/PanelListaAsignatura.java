package UI.Asignatura;

import DAO.AsignaturaDAO;
import Modelos.Asignatura;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class PanelListaAsignatura extends JPanel {

    private final DefaultListModel<String> modelo;

    public PanelListaAsignatura() {

        setLayout(
                new BorderLayout(
                        15,
                        15
                )
        );

        JLabel titulo =
                new JLabel(
                        "Listado de Asignaturas",
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

        modelo =
                new DefaultListModel<>();

        JList<String> lista =
                new JList<>(
                        modelo
                );

        lista.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        JScrollPane scroll =
                new JScrollPane(
                        lista
                );

        add(
                scroll,
                BorderLayout.CENTER
        );

        JButton refrescar =
                new JButton(
                        "Actualizar"
                );

        refrescar.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        refrescar.putClientProperty(
                "JButton.arc",
                20
        );

        refrescar.addActionListener(
                e ->
                        cargar()
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

            AsignaturaDAO dao =
                    new AsignaturaDAO();

            List<Asignatura> lista =
                    dao.obtenerTodas();

            if (
                    lista.isEmpty()
            ) {

                modelo.addElement(
                        "No existen asignaturas registradas."
                );

                return;
            }

            for (
                    Asignatura a
                    :
                    lista
            ) {

                modelo.addElement(
                        "ID: "
                                +
                                a.getIdAsignatura()
                                +
                                " | "
                                +
                                a.getNombre()
                );

            }

        }

        catch (
                Exception ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando asignaturas:\n"
                            +
                            ex.getMessage()
            );

        }

    }

}