package UI;

import javax.swing.*;
import java.awt.*;

public class PanelUsuarios extends JPanel {
    public PanelUsuarios(String s) {
        setLayout(new BorderLayout());
        add(new JLabel(s), BorderLayout.CENTER);
    }
}