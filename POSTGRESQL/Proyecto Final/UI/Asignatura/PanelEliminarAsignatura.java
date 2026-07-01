package UI.Asignatura;

import DAO.AsignaturaDAO;
import Modelos.Asignatura;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelEliminarAsignatura extends JPanel {

    private final DefaultListModel<String> modelo;
    private final JList<String> lista;

    public PanelEliminarAsignatura() {

        setLayout(
                new BorderLayout(
                        15,
                        15
                )
        );

        JLabel titulo =
                new JLabel(
                        "Eliminar Asignatura",
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

        lista =
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

        JPanel inferior =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                20,
                                10
                        )
                );

        JButton eliminar =
                crearBoton(
                        "Eliminar"
                );

        JButton refrescar =
                crearBoton(
                        "Actualizar"
                );

        eliminar.addActionListener(
                e ->
                        eliminar()
        );

        refrescar.addActionListener(
                e ->
                        cargar()
        );

        inferior.add(
                eliminar
        );

        inferior.add(
                refrescar
        );

        add(
                inferior,
                BorderLayout.SOUTH
        );

        cargar();
    }

    private JButton crearBoton(
            String texto
    ) {

        JButton b =
                new JButton(
                        texto
                );

        b.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        b.putClientProperty(
                "JButton.arc",
                20
        );

        return b;
    }

    private void cargar() {

        try {

            modelo.clear();

            AsignaturaDAO dao =
                    new AsignaturaDAO();

            List<Asignatura> listaDatos =
                    dao.obtenerTodas();

            for (
                    Asignatura a
                    :
                    listaDatos
            ) {

                modelo.addElement(
                        a.getIdAsignatura()
                                +
                                " - "
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
                    ex.getMessage()
            );

        }

    }

    private void eliminar() {

        try {

            String seleccionado =
                    lista.getSelectedValue();

            if (
                    seleccionado == null
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona una asignatura."
                );

                return;

            }

            int confirmar =
                    JOptionPane.showConfirmDialog(
                            this,
                            "¿Eliminar asignatura?",
                            "Confirmar",
                            JOptionPane.YES_NO_OPTION
                    );

            if (
                    confirmar
                            !=
                            JOptionPane.YES_OPTION
            ) {

                return;

            }

            int id =
                    Integer.parseInt(
                            seleccionado.split(" - ")[0]
                    );

            AsignaturaDAO dao =
                    new AsignaturaDAO();

            boolean ok =
                    dao.eliminar(
                            id
                    );

            JOptionPane.showMessageDialog(
                    this,
                    ok
                            ?
                            "Asignatura eliminada."
                            :
                            "No se pudo eliminar."
            );

            cargar();

        }

        catch (
                Exception ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );

        }

    }

}