package UI.Dashboard;

import java.awt.*;
import javax.swing.*;

public class Dashboard extends JFrame {

    private JPanel panelContenido;
    private final JPanel contenedorPrincipal;

    public Dashboard() {

        setTitle("Sistema Gestión EDJA");

        setSize(1200, 720);

        setMinimumSize(new Dimension(1000, 650));

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // HEADER
        HeaderPanel header =
                new HeaderPanel();

        // PANEL CENTRAL
        panelContenido = new HomePanel();

        contenedorPrincipal = new JPanel(new BorderLayout());
        contenedorPrincipal.add(crearScrollContenido(panelContenido), BorderLayout.CENTER);
        add(contenedorPrincipal, BorderLayout.CENTER);

        // MENÚ
        MenuLateral menu = new MenuLateral(this);

        add(header, BorderLayout.NORTH);
        add(menu, BorderLayout.WEST);

        setVisible(true);
    }

    public void cambiarPanel(JPanel nuevo) {
        contenedorPrincipal.removeAll();
        panelContenido = nuevo;
        contenedorPrincipal.add(crearScrollContenido(panelContenido), BorderLayout.CENTER);
        contenedorPrincipal.revalidate();
        contenedorPrincipal.repaint();
    }

    private JScrollPane crearScrollContenido(JPanel contenido) {
        JScrollPane scroll = new JScrollPane(contenido);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

}