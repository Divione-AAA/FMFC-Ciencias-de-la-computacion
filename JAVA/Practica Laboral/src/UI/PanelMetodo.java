package UI;

import javax.swing.*;
import java.awt.*;

public class PanelMetodo extends JPanel {
    public PanelMetodo(String s) {
        setLayout(new BorderLayout());
        add(new JLabel(s), BorderLayout.CENTER);
    }
}
