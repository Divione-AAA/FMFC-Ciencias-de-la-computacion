package UI.Asignatura;

import Modelos.Asignatura;
import Servicios.AsignaturaService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelEliminarAsignatura extends JPanel {

    private final DefaultListModel<String> modelo;
    private final JList<String> lista;

    public PanelEliminarAsignatura() {

        setLayout(new BorderLayout(15, 15));

        JLabel titulo = new JLabel(
                "Eliminar Asignatura",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font(
                "Segoe UI",
                Font.BOLD,
                24
        ));

        add(titulo, BorderLayout.NORTH);

        modelo = new DefaultListModel<>();

        lista = new JList<>(modelo);

        lista.setFont(new Font(
                "Segoe UI",
                Font.PLAIN,
                15
        ));

        JScrollPane scroll = new JScrollPane(lista);

        add(scroll, BorderLayout.CENTER);

        JPanel inferior = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        20,
                        10
                )
        );

        JButton eliminar = crearBoton("Eliminar");
        JButton refrescar = crearBoton("Actualizar");

        eliminar.addActionListener(e -> eliminar());
        refrescar.addActionListener(e -> cargar());

        inferior.add(eliminar);
        inferior.add(refrescar);

        add(inferior, BorderLayout.SOUTH);

        cargar();
    }

    private JButton crearBoton(String texto) {

        JButton boton = new JButton(texto);

        boton.setFont(new Font(
                "Segoe UI",
                Font.BOLD,
                15
        ));

        boton.putClientProperty(
                "JButton.arc",
                20
        );

        return boton;
    }

    private void cargar() {

        try {

            modelo.clear();

            AsignaturaService service =
                    new AsignaturaService();

            List<Asignatura> listaDatos =
                    service.obtenerTodas();

            if (listaDatos.isEmpty()) {

                modelo.addElement(
                        "No existen asignaturas registradas."
                );

                return;
            }

            for (Asignatura a : listaDatos) {

                modelo.addElement(
                        a.getCodigoAsignatura()
                                + " - "
                                + a.getNombre()
                );

            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar las asignaturas.\n"
                            + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

    private void eliminar() {

        try {

            String seleccionado =
                    lista.getSelectedValue();

            if (seleccionado == null ||
                    seleccionado.startsWith("No existen")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione una asignatura."
                );

                return;
            }

            int confirmar =
                    JOptionPane.showConfirmDialog(
                            this,
                            "¿Está seguro de eliminar la asignatura?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (confirmar != JOptionPane.YES_OPTION) {
                return;
            }

            int codigo =
                    Integer.parseInt(
                            seleccionado.split(" - ")[0]
                    );

            AsignaturaService service =
                    new AsignaturaService();

            service.eliminar(codigo);

            JOptionPane.showMessageDialog(
                    this,
                    "Asignatura eliminada correctamente."
            );

            cargar();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al eliminar la asignatura.\n"
                            + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

}