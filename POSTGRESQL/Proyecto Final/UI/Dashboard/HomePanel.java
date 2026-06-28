package UI.Dashboard;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    public HomePanel(){

        setLayout(
                new GridBagLayout()
        );

        JLabel titulo=
                new JLabel(
                        """
                        Sistema de Gestión
                        Educación Jóvenes y Adultos
                        """
                );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        34
                )
        );

        add(titulo);
    }

}