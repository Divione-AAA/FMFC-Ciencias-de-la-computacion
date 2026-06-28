package UI.Escuela;

import javax.swing.*;
import java.awt.*;

public class PanelEscuela extends JPanel {

    private JPanel panelContenido;

    public PanelEscuela() {

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
                        "Gestión de Escuelas",
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
                new JPanel(
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
                        "Añadir Escuela"
                );

        JButton btnLista =
                crearBoton(
                        "Ver Escuelas"
                );

        JButton btnEditar =
                crearBoton(
                        "Editar Escuela"
                );

        JButton btnEliminar =
                crearBoton(
                        "Eliminar Escuela"
                );

        btnAdd.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelAddEscuela()
                        )
        );

        btnLista.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelListaEscuela()
                        )
        );

        btnEditar.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelEditarEscuela()
                        )
        );

        btnEliminar.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelEliminarEscuela()
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

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        JLabel texto =
                new JLabel(
                        """
                        Selecciona una acción
                        del menú lateral
                        """
                );

        texto.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        24
                )
        );

        panel.add(
                texto
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