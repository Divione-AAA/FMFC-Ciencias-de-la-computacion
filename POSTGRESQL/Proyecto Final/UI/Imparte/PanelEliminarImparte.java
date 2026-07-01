package UI.Imparte;

import Servicios.ImparteService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelEliminarImparte extends JPanel {

    private final JTextField campoId;

    private final DefaultListModel<String> modelo;

    public PanelEliminarImparte() {

        setLayout(
                new BorderLayout(
                        15,
                        15
                )
        );

        JLabel titulo =
                new JLabel(
                        "Eliminar Asignación",
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

        JPanel centro =
                new JPanel();

        centro.setLayout(
                new BoxLayout(
                        centro,
                        BoxLayout.Y_AXIS
                )
        );

        centro.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        80,
                        20,
                        80
                )
        );

        JLabel etiqueta =
                new JLabel(
                        "ID Imparte:"
                );

        campoId =
                new JTextField();

        campoId.setMaximumSize(
                new Dimension(
                        300,
                        40
                )
        );

        JButton eliminar =
                new JButton(
                        "Eliminar"
                );

        eliminar.putClientProperty(
                "JButton.arc",
                20
        );

        eliminar.addActionListener(
                e ->
                        eliminar()
        );

        centro.add(
                etiqueta
        );

        centro.add(
                campoId
        );

        centro.add(
                Box.createVerticalStrut(
                        20
                )
        );

        centro.add(
                eliminar
        );

        add(
                centro,
                BorderLayout.WEST
        );

        modelo =
                new DefaultListModel<>();

        JList<String> lista =
                new JList<>(
                        modelo
                );

        JScrollPane scroll =
                new JScrollPane(
                        lista
                );

        add(
                scroll,
                BorderLayout.CENTER
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
                    ex.getMessage()
            );

        }

    }

    private void eliminar() {

        try {

            int id =
                    Integer.parseInt(
                            campoId.getText()
                    );

            ImparteService service =
                    new ImparteService();

            boolean eliminado =
                    service.eliminar(
                            id
                    );

            if (
                    eliminado
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Asignación eliminada."
                );

                campoId.setText("");

                cargar();

            }

            else {

                JOptionPane.showMessageDialog(
                        this,
                        "No existe ese ID."
                );

            }

        }

        catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Introduce un ID válido."
            );

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