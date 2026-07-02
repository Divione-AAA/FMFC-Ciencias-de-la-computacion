package UI.Reportes;

import java.awt.*;
import javax.swing.*;

public class PanelListaReportes extends JPanel {

    public PanelListaReportes() {

        setLayout(
                new BorderLayout(
                        20,
                        20
                )
        );

        JPanel panelBotones =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                20
                        )
                );

        JButton alumnos =
                crearBoton(
                        "📚 Listado de alumnos"
                );

        JButton profesores =
                crearBoton(
                        "👩 Profesores"
                );

        JButton grupos =
                crearBoton(
                        "🏫 Grupos"
                );

        alumnos.addActionListener(
                e ->
                        JOptionPane.showMessageDialog(
                                this,
                                "Pendiente conectar con servicio."
                        )
        );

        profesores.addActionListener(
                e ->
                        JOptionPane.showMessageDialog(
                                this,
                                "Pendiente conectar con servicio."
                        )
        );

        grupos.addActionListener(
                e ->
                        JOptionPane.showMessageDialog(
                                this,
                                "Pendiente conectar con servicio."
                        )
        );

        panelBotones.add(
                alumnos
        );

        panelBotones.add(
                profesores
        );

        panelBotones.add(
                grupos
        );

        add(
                panelBotones,
                BorderLayout.CENTER
        );

    }

    private JButton crearBoton(
            String texto
    ) {

        JButton b =
                new JButton(
                        texto
                );

        b.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b.setPreferredSize(new Dimension(180, 120));
        b.setMinimumSize(new Dimension(160, 110));
        b.setMaximumSize(new Dimension(220, 140));

        b.putClientProperty(
                "JButton.arc",
                20
        );

        return b;

    }

}