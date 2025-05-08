package UI;

import javax.swing.*;
import java.awt.*;

public class PanelGestion extends JPanel {
    public PanelGestion(String s) {
        setLayout(new BorderLayout());
        add(new JLabel(s), BorderLayout.CENTER);
    }
}