package UI.Imparte;

import javax.swing.*;
import java.awt.*;

public class PanelImparte extends JPanel {

    public PanelImparte() {

        setLayout(
                new BorderLayout(
                        15,
                        15
                )
        );

        JLabel titulo =
                new JLabel(
                        "Gestión de Impartición",
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

        JTabbedPane tabs =
                new JTabbedPane();

        tabs.putClientProperty(
                "JTabbedPane.tabHeight",
                45
        );

        tabs.addTab(
                "➕ Asignar",
                new PanelAddImparte()
        );

        tabs.addTab(
                "📋 Listar",
                new PanelListaImparte()
        );

        tabs.addTab(
                "🗑 Eliminar",
                new PanelEliminarImparte()
        );

        add(
                tabs,
                BorderLayout.CENTER
        );

    }

}