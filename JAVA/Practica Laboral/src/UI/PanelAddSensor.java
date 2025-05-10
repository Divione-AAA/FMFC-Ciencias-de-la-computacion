package UI;

import javax.swing.*;
import java.awt.*;

public class PanelAddSensor extends JPanel {
    public PanelAddSensor(String s){
        setLayout(new BorderLayout());
        add(new JLabel(s), BorderLayout.CENTER);
    }
}
