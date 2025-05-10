package UI;

import javax.swing.*;
import java.awt.*;

public class PanelDelSensor extends JPanel {
    public PanelDelSensor(String s) {
        setLayout(new BorderLayout());
        add(new JLabel(s), BorderLayout.CENTER);
    }
}