package UI.Imparte;

import Modelos.Imparte;
import Servicios.ImparteService;

import javax.swing.*;
import java.awt.*;

public class PanelAddImparte extends JPanel {

    private final JTextField campoProfesor;
    private final JTextField campoGrupo;
    private final JTextField campoAsignatura;

    public PanelAddImparte() {

        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        40,
                        25,
                        40
                )
        );

        campoProfesor = crearCampo();
        campoGrupo = crearCampo();
        campoAsignatura = crearCampo();

        JButton botonGuardar =
                new JButton(
                        "Asignar Profesor"
                );

        botonGuardar.putClientProperty(
                "JButton.arc",
                20
        );

        botonGuardar.addActionListener(
                e -> guardar()
        );

        panel.add(
                new JLabel(
                        "ID Profesor:"
                )
        );

        panel.add(
                campoProfesor
        );

        panel.add(
                Box.createVerticalStrut(15)
        );

        panel.add(
                new JLabel(
                        "Código Grupo:"
                )
        );

        panel.add(
                campoGrupo
        );

        panel.add(
                Box.createVerticalStrut(15)
        );

        panel.add(
                new JLabel(
                        "ID Asignatura:"
                )
        );

        panel.add(
                campoAsignatura
        );

        panel.add(
                Box.createVerticalStrut(25)
        );

        panel.add(
                botonGuardar
        );

        add(panel);

    }

    private JTextField crearCampo() {

        JTextField campo =
                new JTextField();

        campo.setMaximumSize(
                new Dimension(
                        350,
                        40
                )
        );

        return campo;

    }

    private void guardar() {

        try {

            int profesor =
                    Integer.parseInt(
                            campoProfesor.getText()
                    );

            int grupo =
                    Integer.parseInt(
                            campoGrupo.getText()
                    );

            int asignatura =
                    Integer.parseInt(
                            campoAsignatura.getText()
                    );

            Imparte nueva =
                    new Imparte(
                            0,
                            profesor,
                            grupo,
                            asignatura
                    );

            ImparteService service =
                    new ImparteService();

            service.asignar(
                    nueva
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Asignación creada correctamente."
            );

            campoProfesor.setText("");

            campoGrupo.setText("");

            campoAsignatura.setText("");

        }

        catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Los IDs deben ser numéricos."
            );

        }

        catch (
                Exception ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error:\n"
                            +
                            ex.getMessage()
            );

        }

    }

}