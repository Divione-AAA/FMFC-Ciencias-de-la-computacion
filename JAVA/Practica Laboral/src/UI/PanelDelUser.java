package UI;

import javax.swing.*;
import java.awt.*;

public class PanelDelUser extends JPanel {
    public PanelDelUser(String s) {
        setLayout(new BorderLayout());
        add(new JLabel(s), BorderLayout.CENTER);
    }
}