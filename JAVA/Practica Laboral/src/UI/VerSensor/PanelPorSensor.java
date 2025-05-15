package UI.VerSensor;
import javax.swing.*;
import java.awt.*;

public class PanelPorSensor extends JPanel {
    public PanelPorSensor(String s) {
        setLayout(new BorderLayout());
        add(new JLabel(s), BorderLayout.CENTER);
    }
}
