package UI.Imparte;

import Servicios.ImparteService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelListaImparte extends JPanel {

    private final DefaultListModel<String> modelo;

    public PanelListaImparte() {

        setLayout(
                new BorderLayout(
                        15,
                        15
                )
        );

        JLabel titulo =
                new JLabel(
                        "Asignaciones Registradas",
                        SwingConstants.CENTER
                );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
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

        refrescar.putClientProperty(
                "JButton.arc",
                20
        );

        refrescar.addActionListener(
                e ->
                        cargar()
        );

        JPanel abajo =
                new JPanel();

        abajo.add(
                refrescar
        );

        add(
                abajo,
                BorderLayout.SOUTH
        );

        cargar();

    }

    private void cargar() {

        try {

            modelo.clear();

            ImparteService service =
                    new ImparteService();

            List<String> datos =
                    service.obtenerVistaCompleta();

            if (
                    datos.isEmpty()
            ) {

                modelo.addElement(
                        "No existen asignaciones."
                );

                return;

            }

            for (
                    String fila
                    :
                    datos
            ) {

                modelo.addElement(
                        fila
                );

            }

        }

        catch (
                Exception ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando asignaciones:\n"
                            +
                            ex.getMessage()
            );

        }

    }

}