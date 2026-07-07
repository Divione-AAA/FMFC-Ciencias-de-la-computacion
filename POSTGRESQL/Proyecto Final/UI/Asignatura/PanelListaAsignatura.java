package UI.Asignatura;

import Modelos.Asignatura;
import Servicios.AsignaturaService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelListaAsignatura extends JPanel {

    private final DefaultListModel<String> modelo;

    public PanelListaAsignatura() {

        setLayout(new BorderLayout(15, 15));

        JLabel titulo = new JLabel(
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

        add(titulo, BorderLayout.NORTH);

        modelo = new DefaultListModel<>();

        JList<String> lista = new JList<>(modelo);

        lista.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        JScrollPane scroll = new JScrollPane(lista);

        add(scroll, BorderLayout.CENTER);

        JButton refrescar = new JButton("Actualizar");

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

        refrescar.addActionListener(e -> cargar());

        JPanel inferior = new JPanel();

        inferior.add(refrescar);

        add(inferior, BorderLayout.SOUTH);

        cargar();
    }

    private void cargar() {

        try {

            modelo.clear();

            AsignaturaService service =
                    new AsignaturaService();

            List<Asignatura> lista =
                    service.obtenerTodas();

            if (lista.isEmpty()) {

                modelo.addElement(
                        "No existen asignaturas registradas."
                );

                return;

            }

            for (Asignatura a : lista) {

                modelo.addElement(
                        "Código: "
                                + a.getCodigoAsignatura()
                                + " | Nombre: "
                                + a.getNombre()
                );

            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando asignaturas:\n"
                            + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

}