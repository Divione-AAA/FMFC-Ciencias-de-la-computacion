package UI.VerEntorno;

import javax.swing.*;
import java.awt.*;

public class PanelEntorno extends JPanel {
    public PanelEntorno(String s) {
        setLayout(new BorderLayout());
        add(new JLabel(s), BorderLayout.CENTER);
    }
}