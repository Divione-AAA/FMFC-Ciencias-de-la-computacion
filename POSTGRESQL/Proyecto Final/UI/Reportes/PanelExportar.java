package UI.Reportes;

import javax.swing.*;
import java.awt.*;

public class PanelExportar extends JPanel {

    public PanelExportar() {

        setLayout(
                new GridBagLayout()
        );

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        JButton pdf =
                new JButton(
                        "Exportar PDF"
                );

        JButton csv =
                new JButton(
                        "Exportar CSV"
                );

        pdf.putClientProperty(
                "JButton.arc",
                20
        );

        csv.putClientProperty(
                "JButton.arc",
                20
        );

        pdf.addActionListener(
                e ->
                        JOptionPane.showMessageDialog(
                                this,
                                "Exportación PDF pendiente."
                        )
        );

        csv.addActionListener(
                e ->
                        JOptionPane.showMessageDialog(
                                this,
                                "Exportación CSV pendiente."
                        )
        );

        panel.add(
                pdf
        );

        panel.add(
                Box.createVerticalStrut(
                        20
                )
        );

        panel.add(
                csv
        );

        add(
                panel
        );

    }

}