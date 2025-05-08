package UI;

import javax.swing.*;
import java.awt.*;

public class PanelAddUsers extends JPanel {
    public PanelAddUsers(String s){
        setLayout(new BorderLayout());
        add(new JLabel(s), BorderLayout.CENTER);
    }
}
