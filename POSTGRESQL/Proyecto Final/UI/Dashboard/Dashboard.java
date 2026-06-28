package UI.Dashboard;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    private JPanel panelContenido;

    public Dashboard() {

        setTitle("Sistema Gestión EDJA");

        setSize(1500, 900);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // HEADER
        HeaderPanel header =
                new HeaderPanel();

        // PANEL CENTRAL
        panelContenido =
                new HomePanel();

        // MENÚ
        MenuLateral menu =
                new MenuLateral(this);

        add(
                header,
                BorderLayout.NORTH
        );

        add(
                menu,
                BorderLayout.WEST
        );

        add(
                panelContenido,
                BorderLayout.CENTER
        );

        setVisible(true);
    }

    public void cambiarPanel(
            JPanel nuevo
    ) {

        remove(
                panelContenido
        );

        panelContenido =
                nuevo;

        add(
                panelContenido,
                BorderLayout.CENTER
        );

        revalidate();

        repaint();
    }

}