package UI.Grupo;

import javax.swing.*;
import java.awt.*;

public class PanelGrupo extends JPanel {

    private JPanel panelContenido;

    public PanelGrupo() {

        setLayout(new BorderLayout(20,20));

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
                        "Gestión de Grupos",
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

        JButton agregar =
                crearBoton(
                        "Añadir Grupo"
                );

        JButton listar =
                crearBoton(
                        "Ver Grupos"
                );

        JButton editar =
                crearBoton(
                        "Editar Grupo"
                );

        JButton eliminar =
                crearBoton(
                        "Eliminar Grupo"
                );

        agregar.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelAddGrupo()
                        )
        );

        listar.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelListaGrupo()
                        )
        );

        editar.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelEditarGrupo()
                        )
        );

        eliminar.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelEliminarGrupo()
                        )
        );

        panel.add(agregar);
        panel.add(listar);
        panel.add(editar);
        panel.add(eliminar);

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