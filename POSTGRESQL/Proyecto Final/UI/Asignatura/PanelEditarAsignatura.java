package UI.Asignatura;

import Modelos.Asignatura;
import Servicios.AsignaturaService;

import javax.swing.*;
import java.awt.*;

public class PanelEditarAsignatura extends JPanel {

    private final JTextField campoCodigo;
    private final JTextField campoNombre;

    public PanelEditarAsignatura() {

        setLayout(new BorderLayout(15, 15));

        JLabel titulo = new JLabel(
                "Editar Asignatura",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font(
                "Segoe UI",
                Font.BOLD,
                24
        ));

        add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel();

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

        campoCodigo = crearCampo();
        campoNombre = crearCampo();

        JButton buscar = crearBoton("Buscar");
        JButton guardar = crearBoton("Guardar Cambios");

        buscar.addActionListener(e -> buscar());
        guardar.addActionListener(e -> guardar());

        centro.add(etiqueta("Código"));
        centro.add(campoCodigo);

        centro.add(Box.createVerticalStrut(15));

        centro.add(buscar);

        centro.add(Box.createVerticalStrut(25));

        centro.add(etiqueta("Nombre"));
        centro.add(campoNombre);

        centro.add(Box.createVerticalStrut(25));

        centro.add(guardar);

        add(centro, BorderLayout.CENTER);

    }

    private JTextField crearCampo() {

        JTextField t = new JTextField();

        t.setMaximumSize(
                new Dimension(
                        400,
                        40
                )
        );

        return t;

    }

    private JLabel etiqueta(String texto) {

        JLabel l = new JLabel(texto);

        l.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        return l;

    }

    private JButton crearBoton(String texto) {

        JButton b = new JButton(texto);

        b.putClientProperty(
                "JButton.arc",
                20
        );

        b.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return b;

    }

    private void buscar() {

        try {

            int codigo = Integer.parseInt(
                    campoCodigo.getText().trim()
            );

            AsignaturaService service =
                    new AsignaturaService();

            Asignatura asignatura =
                    service.buscarPorCodigo(codigo);

            if (asignatura == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Asignatura no encontrada."
                );

                return;

            }

            campoNombre.setText(
                    asignatura.getNombre()
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Código inválido."
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );

        }

    }

    private void guardar() {

        try {

            int codigo = Integer.parseInt(
                    campoCodigo.getText().trim()
            );

            String nombre =
                    campoNombre.getText().trim();

            if (nombre.isBlank()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Debe escribir el nombre."
                );

                return;

            }

            Asignatura asignatura =
                    new Asignatura(
                            codigo,
                            nombre
                    );

            AsignaturaService service =
                    new AsignaturaService();

            service.actualizar(asignatura);

            JOptionPane.showMessageDialog(
                    this,
                    "Asignatura actualizada correctamente."
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Código inválido."
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );

        }

    }

}