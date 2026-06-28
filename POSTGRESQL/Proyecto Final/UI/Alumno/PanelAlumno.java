package UI.Alumno;

import javax.swing.*;
import java.awt.*;

public class PanelAlumno extends JPanel {

    private JPanel panelContenido;

    public PanelAlumno() {

        setLayout(
                new BorderLayout(
                        20,
                        20
                )
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JLabel titulo =
                new JLabel(
                        "Gestión de Alumnos",
                        SwingConstants.CENTER
                );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        add(
                titulo,
                BorderLayout.NORTH
        );

        JPanel menu =
                crearMenu();

        panelContenido =
                new JPanel(
                        new BorderLayout()
                );

        panelContenido.add(
                crearBienvenida(),
                BorderLayout.CENTER
        );

        add(
                menu,
                BorderLayout.WEST
        );

        add(
                panelContenido,
                BorderLayout.CENTER
        );
    }

    private JPanel crearMenu() {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new GridLayout(
                        4,
                        1,
                        10,
                        10
                )
        );

        panel.setPreferredSize(
                new Dimension(
                        240,
                        0
                )
        );

        JButton btnAdd =
                crearBoton(
                        "Añadir Alumno"
                );

        JButton btnLista =
                crearBoton(
                        "Ver Alumnos"
                );

        JButton btnEditar =
                crearBoton(
                        "Editar Alumno"
                );

        JButton btnEliminar =
                crearBoton(
                        "Eliminar Alumno"
                );

        btnAdd.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelAddAlumno()
                        )
        );

        btnLista.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelListaAlumno()
                        )
        );

        btnEditar.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelEditarAlumno()
                        )
        );

        btnEliminar.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelEliminarAlumno()
                        )
        );

        panel.add(btnAdd);

        panel.add(btnLista);

        panel.add(btnEditar);

        panel.add(btnEliminar);

        return panel;
    }

    private JButton crearBoton(
            String texto
    ) {

        JButton b =
                new JButton(
                        texto
                );

        b.setPreferredSize(
                new Dimension(
                        220,
                        70
                )
        );

        b.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        b.putClientProperty(
                "JButton.arc",
                20
        );

        return b;
    }

    private JPanel crearBienvenida() {

        JPanel p =
                new JPanel(
                        new GridBagLayout()
                );

        JLabel l =
                new JLabel(
                        """
                        Selecciona una acción
                        del menú lateral
                        """
                );

        l.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        24
                )
        );

        p.add(l);

        return p;
    }

    private void cambiarPanel(
            JPanel panel
    ) {

        panelContenido.removeAll();

        panelContenido.add(
                panel,
                BorderLayout.CENTER
        );

        panelContenido.revalidate();

        panelContenido.repaint();
    }

}