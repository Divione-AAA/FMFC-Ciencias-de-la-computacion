package UI.GestionSensores;

import javax.swing.*;
import java.awt.*;

public class PanelGestion extends JPanel {
    public PanelGestion(String rol) {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Comprobación de acceso
        if (!"admin".equalsIgnoreCase(rol)) {
            JLabel mensaje = new JLabel("Acceso denegado. Solo disponible para administradores.", SwingConstants.CENTER);
            mensaje.setFont(new Font("Segoe UI", Font.BOLD, 18));
            add(mensaje, BorderLayout.CENTER);
            return;
        }

        try {
            PanelSelectorSensor selector = new PanelSelectorSensor();
            PanelAgregarValor panelAgregar = new PanelAgregarValor(selector);
            PanelListaValores panelValores = new PanelListaValores(selector);

            JPanel centro = new JPanel(new BorderLayout(10, 10));
            centro.add(panelAgregar, BorderLayout.NORTH);
            centro.add(panelValores, BorderLayout.CENTER);

            add(selector, BorderLayout.WEST);
            add(centro, BorderLayout.CENTER);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar PanelGestion: " + e.getMessage());
        }
    }
}
