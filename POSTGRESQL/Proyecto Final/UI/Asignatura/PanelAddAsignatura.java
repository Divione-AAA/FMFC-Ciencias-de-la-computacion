package UI.Asignatura;

import DAO.AsignaturaDAO;
import Modelos.Asignatura;

import javax.swing.*;
import java.awt.*;

public class PanelAddAsignatura extends JPanel {

    private final JTextField campoCodigoAsignatura = new JTextField();
    private  JTextField campoNombre = new JTextField();

    public PanelAddAsignatura() {

        setLayout(new BorderLayout());

        JLabel titulo =
                new JLabel(
                        "Registrar Asignatura",
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

        JPanel formulario =
                new JPanel();

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

        Dimension tam =
                new Dimension(
                        400,
                        40
                );

        // NOMBRE

        JLabel labelNombre =
                new JLabel(
                        "Nombre:"
                );

        labelNombre.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        campoNombre =
                new JTextField();

        campoNombre.setMaximumSize(
                tam
        );

        campoNombre.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        JLabel labelCodigoAsignatura =
                new JLabel(
                        "Código de asignatura:"
                );

        labelCodigoAsignatura.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        campoCodigoAsignatura.setMaximumSize(
                tam
        );

        campoCodigoAsignatura.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        JButton guardar =
                new JButton(
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

        formulario.add(
                labelCodigoAsignatura
        );

        formulario.add(
                campoCodigoAsignatura
        );

        formulario.add(
                Box.createVerticalStrut(
                        20
                )
        );

        formulario.add(
                labelNombre
        );

        formulario.add(
                campoNombre
        );

        formulario.add(
                Box.createVerticalStrut(
                        25
                )
        );

        formulario.add(
                guardar
        );

        JPanel wrapper =
                new JPanel(
                        new GridBagLayout()
                );

        wrapper.add(
                formulario
        );

        add(
                wrapper,
                BorderLayout.CENTER
        );
    }

    private void guardarAsignatura() {

        try {

            String codigoAsignatura =
                    campoCodigoAsignatura
                            .getText()
                            .trim();

            String nombre =
                    campoNombre
                            .getText()
                            .trim();

            if (
                    codigoAsignatura.isEmpty() || nombre.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Debe introducir el nombre."
                );

                return;
            }

            Asignatura asignatura =
                    new Asignatura(
                            0,
                            codigoAsignatura,
                            nombre
                    );

            AsignaturaDAO dao =
                    new AsignaturaDAO();

            dao.insertar(
                    asignatura
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Asignatura registrada correctamente."
            );

            limpiar();

        }

        catch (
                Exception ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: "
                            +
                            ex.getMessage()
            );

        }

    }

    private void limpiar() {

        campoCodigoAsignatura.setText("");

        campoNombre.setText("");

    }

}