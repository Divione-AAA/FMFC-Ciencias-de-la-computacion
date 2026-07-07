package UI.Asignatura;

import Modelos.Asignatura;
import Servicios.AsignaturaService;

import javax.swing.*;
import java.awt.*;

public class PanelAddAsignatura extends JPanel {

    private JTextField campoNombre;

    public PanelAddAsignatura() {

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel(
                "Registrar Asignatura",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font(
                "Segoe UI",
                Font.BOLD,
                24
        ));

        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel();

        formulario.setLayout(
                new BoxLayout(
                        formulario,
                        BoxLayout.Y_AXIS
                )
        );

        formulario.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        80,
                        20,
                        80
                )
        );

        Dimension tam = new Dimension(
                400,
                40
        );

        JLabel labelNombre = new JLabel(
                "Nombre:"
        );

        labelNombre.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        campoNombre = new JTextField();

        campoNombre.setMaximumSize(tam);

        campoNombre.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        JButton guardar = new JButton(
                "Guardar"
        );

        guardar.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        guardar.putClientProperty(
                "JButton.arc",
                20
        );

        guardar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        guardar.addActionListener(
                e -> guardarAsignatura()
        );

        formulario.add(labelNombre);
        formulario.add(campoNombre);

        formulario.add(
                Box.createVerticalStrut(25)
        );

        formulario.add(guardar);

        JPanel wrapper = new JPanel(
                new GridBagLayout()
        );

        wrapper.add(formulario);

        add(
                wrapper,
                BorderLayout.CENTER
        );
    }

    private void guardarAsignatura() {

        try {

            String nombre =
                    campoNombre
                            .getText()
                            .trim();

            if (nombre.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Debe introducir el nombre de la asignatura."
                );

                return;
            }

            Asignatura asignatura =
                    new Asignatura(
                            0,
                            nombre
                    );

            AsignaturaService service =
                    new AsignaturaService();

            service.registrar(asignatura);

            JOptionPane.showMessageDialog(
                    this,
                    "Asignatura registrada correctamente."
            );

            limpiar();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

    private void limpiar() {

        campoNombre.setText("");

    }

}