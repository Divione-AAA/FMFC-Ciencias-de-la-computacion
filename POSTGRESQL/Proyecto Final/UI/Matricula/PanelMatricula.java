package UI.Matricula;

import javax.swing.*;
import java.awt.*;

public class PanelMatricula extends JPanel {

    public PanelMatricula() {

        setLayout(
                new BorderLayout(
                        15,
                        15
                )
        );

        JLabel titulo =
                new JLabel(
                        "Gestión de Matrículas",
                        SwingConstants.CENTER
                );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        add(
                titulo,
                BorderLayout.NORTH
        );

        JTabbedPane tabs =
                new JTabbedPane();

        tabs.putClientProperty(
                "JTabbedPane.tabHeight",
                45
        );

        tabs.addTab(
                "➕ Nueva",
                new PanelNuevaMatricula()
        );

        tabs.addTab(
                "📋 Listar",
                new PanelListaMatricula()
        );

        tabs.addTab(
                "✏ Editar",
                new PanelEditarMatricula()
        );

        tabs.addTab(
                "🗑 Eliminar",
                new PanelEliminarMatricula()
        );

        add(
                tabs,
                BorderLayout.CENTER
        );

    }

}