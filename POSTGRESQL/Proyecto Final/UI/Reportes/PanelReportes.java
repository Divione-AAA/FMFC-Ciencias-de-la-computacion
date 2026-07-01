package UI.Reportes;

import javax.swing.*;
import java.awt.*;

public class PanelReportes extends JPanel {

    public PanelReportes() {

        setLayout(
                new BorderLayout(
                        15,
                        15
                )
        );

        JLabel titulo =
                new JLabel(
                        "Reportes del Sistema",
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
                "📋 Listados",
                new PanelListaReportes()
        );

        tabs.addTab(
                "📄 Exportar",
                new PanelExportar()
        );

        add(
                tabs,
                BorderLayout.CENTER
        );

    }

}