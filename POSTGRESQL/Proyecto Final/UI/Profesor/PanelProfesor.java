package UI.Profesor;

import javax.swing.*;
import java.awt.*;

public class PanelProfesor extends JPanel {

    private JPanel panelContenido;

    public PanelProfesor() {

        setLayout(new BorderLayout(20, 20));

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
                        "Gestión de Profesores",
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
                        "Añadir Profesor"
                );

        JButton btnLista =
                crearBoton(
                        "Ver Profesores"
                );

        JButton btnEditar =
                crearBoton(
                        "Editar Profesor"
                );

        JButton btnEliminar =
                crearBoton(
                        "Eliminar Profesor"
                );

        btnAdd.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelAddProfesor()
                        )
        );

        btnLista.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelListaProfesor()
                        )
        );

        btnEditar.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelEditarProfesor()
                        )
        );

        btnEliminar.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelEliminarProfesor()
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

        JButton boton =
                new JButton(
                        texto
                );

        boton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        boton.setPreferredSize(
                new Dimension(
                        220,
                        70
                )
        );

        boton.putClientProperty(
                "JButton.arc",
                20
        );

        return boton;
    }

    private JPanel crearBienvenida() {

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        JLabel mensaje =
                new JLabel(
                        """
                        Selecciona una acción
                        del menú lateral
                        """
                );

        mensaje.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        24
                )
        );

        panel.add(
                mensaje
        );

        return panel;
    }

    private void cambiarPanel(
            JPanel nuevo
    ) {

        panelContenido.removeAll();

        panelContenido.add(
                nuevo,
                BorderLayout.CENTER
        );

        panelContenido.revalidate();

        panelContenido.repaint();
    }

}