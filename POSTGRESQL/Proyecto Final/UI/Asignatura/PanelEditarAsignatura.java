package UI.Asignatura;

import DAO.AsignaturaDAO;
import Modelos.Asignatura;
import java.awt.*;
import javax.swing.*;

public class PanelEditarAsignatura extends JPanel {

    private final JTextField campoId;
    private final JTextField campoCodigoAsignatura;
    private final JTextField campoNombre;

    public PanelEditarAsignatura() {

        setLayout(
                new BorderLayout(
                        15,
                        15
                )
        );

        JLabel titulo =
                new JLabel(
                        "Editar Asignatura",
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
                        100,
                        20,
                        100
                )
        );

        campoId =
                crearCampo();

        campoCodigoAsignatura =
                crearCampo();

        campoNombre =
                crearCampo();

        JButton buscar =
                crearBoton(
                        "Buscar"
                );

        JButton guardar =
                crearBoton(
                        "Guardar Cambios"
                );

        buscar.addActionListener(
                e ->
                        buscar()
        );

        guardar.addActionListener(
                e ->
                        guardar()
        );

        centro.add(
                etiqueta(
                        "ID"
                )
        );

        centro.add(
                campoId
        );

        centro.add(
                Box.createVerticalStrut(
                        10
                )
        );

        centro.add(
                buscar
        );

        centro.add(
                Box.createVerticalStrut(
                        20
                )
        );

        centro.add(
                etiqueta(
                        "Código"
                )
        );

        centro.add(
                campoCodigoAsignatura
        );

        centro.add(
                Box.createVerticalStrut(
                        20
                )
        );

        centro.add(
                etiqueta(
                        "Nombre"
                )
        );

        centro.add(
                campoNombre
        );

        centro.add(
                Box.createVerticalStrut(
                        20
                )
        );

        centro.add(
                guardar
        );

        add(
                centro,
                BorderLayout.CENTER
        );
    }

    private JTextField crearCampo() {

        JTextField t =
                new JTextField();

        t.setMaximumSize(
                new Dimension(
                        400,
                        40
                )
        );

        return t;
    }

    private JLabel etiqueta(
            String txt
    ) {

        return new JLabel(
                txt
        );

    }

    private JButton crearBoton(
            String txt
    ) {

        JButton b =
                new JButton(
                        txt
                );

        b.putClientProperty(
                "JButton.arc",
                20
        );

        return b;
    }

    private void buscar() {

        try {

            int id =
                    Integer.parseInt(
                            campoId.getText()
                    );

            AsignaturaDAO dao =
                    new AsignaturaDAO();

            Asignatura a =
                    dao.obtenerPorId(
                            id
                    );

            if (
                    a == null
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "No encontrada"
                );

                return;
            }

            campoCodigoAsignatura.setText(
                    a.getCodigoAsignatura()
            );

            campoNombre.setText(
                    a.getNombre()
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

    private void guardar() {

        try {

            Asignatura a =
                    new Asignatura(
                            Integer.parseInt(
                                    campoId.getText()
                            ),
                            campoCodigoAsignatura.getText(),
                            campoNombre.getText()
                    );

            AsignaturaDAO dao =
                    new AsignaturaDAO();

            boolean ok =
                    dao.actualizar(
                            a
                    );

            JOptionPane.showMessageDialog(
                    this,
                    ok
                            ? "Actualizada"
                            : "No se pudo actualizar"
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