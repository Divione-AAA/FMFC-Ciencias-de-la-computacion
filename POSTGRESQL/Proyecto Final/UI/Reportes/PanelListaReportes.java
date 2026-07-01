package UI.Reportes;

import javax.swing.*;
import java.awt.*;

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
                                3,
                                1,
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

        b.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        b.putClientProperty(
                "JButton.arc",
                20
        );

        return b;

    }

}