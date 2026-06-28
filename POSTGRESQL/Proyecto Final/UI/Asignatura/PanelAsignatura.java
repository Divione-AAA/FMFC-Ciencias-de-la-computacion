package UI.Asignatura;

import javax.swing.*;
import java.awt.*;

public class PanelAsignatura extends JPanel {

    private final JPanel panelContenido;

    public PanelAsignatura() {

        setLayout(
                new BorderLayout(
                        15,
                        15
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
                        "Gestión de Asignaturas",
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

        // Panel botones
        JPanel panelBotones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                20,
                                10
                        )
                );

        JButton btnAgregar =
                crearBoton(
                        "➕ Registrar"
                );

        JButton btnLista =
                crearBoton(
                        "📋 Consultar"
                );

        JButton btnEditar =
                crearBoton(
                        "✏ Editar"
                );

        JButton btnEliminar =
                crearBoton(
                        "🗑 Eliminar"
                );

        panelBotones.add(btnAgregar);

        panelBotones.add(btnLista);

        panelBotones.add(btnEditar);

        panelBotones.add(btnEliminar);

        add(
                panelBotones,
                BorderLayout.SOUTH
        );

        panelContenido =
                new JPanel(
                        new BorderLayout()
                );

        panelContenido.add(
                new JLabel(
                        "Seleccione una opción",
                        SwingConstants.CENTER
                ),
                BorderLayout.CENTER
        );

        add(
                panelContenido,
                BorderLayout.CENTER
        );

        // EVENTOS

        btnAgregar.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelAddAsignatura()
                        )
        );

        btnLista.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelListaAsignatura()
                        )
        );

        btnEditar.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelEditarAsignatura()
                        )
        );

        btnEliminar.addActionListener(
                e ->
                        cambiarPanel(
                                new PanelEliminarAsignatura()
                        )
        );

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
                        180,
                        55
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