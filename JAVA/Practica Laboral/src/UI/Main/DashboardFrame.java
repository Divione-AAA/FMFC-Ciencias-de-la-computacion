package UI.Main;

import UI.AddSensor.PanelAddSensor;
import UI.Ayuda.PanelAbout;
import UI.Ayuda.PanelWellcome;
import UI.DelSensor.PanelDelSensor;
import UI.GestionMetodo.PanelMetodo;
import UI.GestionSensores.PanelGestion;
import UI.GestionUsuario.PanelUsuarios;
import UI.VerAlertas.PanelAlertas;
import UI.VerEntorno.PanelEntorno;
import UI.VerSensor.PanelPorSensor;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class DashboardFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private HashMap<String, JPanel> paneles;

    public DashboardFrame(String usuario, String rol, Connection conn) throws SQLException {
        setTitle("Panel de Control - Bienvenido " + usuario);
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginUI.class.getResource("/resources/icon.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Zona central con CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        paneles = new HashMap<>();

        // Crear formularios y agregarlo
        addFormPanel("Usuarios", new PanelUsuarios("Gestión de usuarios",rol,conn,usuario));
        addFormPanel("Añadir", new PanelAddSensor(rol));
        addFormPanel("Eliminar", new PanelDelSensor(rol));
        addFormPanel("Gestionar", new PanelGestion(rol));
        addFormPanel("Método de Alerta", new PanelMetodo(rol));
        addFormPanel("Datos por sensor", new PanelPorSensor("Datos por sensor"));
        addFormPanel("Entorno", new PanelEntorno("Datos por Entorno"));
        addFormPanel("Alertas", new PanelAlertas("Alertas"));
        addFormPanel("Bienvenido", new PanelWellcome("¡Bienvenido al sistema!"));
        addFormPanel("Acerca de", new PanelAbout("¡Bienvenido"));

        add(mainPanel, BorderLayout.CENTER);

        // Crear barra de menú
        setJMenuBar(crearMenuBar());
        setVisible(true);
    }

    private void addFormPanel(String nombre, JComponent componente) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(componente, BorderLayout.CENTER);
        paneles.put(nombre, panel);
        mainPanel.add(panel, nombre);
    }

    private JMenuBar crearMenuBar() {

        JMenuBar menuBar = new JMenuBar();

        // Inicio
        JMenu menuInicio = new JMenu("Inicio");
        menuInicio.add(crearMenuItem("Usuarios"));
        menuInicio.add(crearMenuItem("Cerrar"));
        menuInicio.add(crearMenuItem("Salir"));

        // Administrar
        JMenu menuAdmin = new JMenu("Administrar");
        menuAdmin.add(crearMenuItem("Añadir"));
        menuAdmin.add(crearMenuItem("Eliminar"));
        menuAdmin.add(crearMenuItem("Gestionar"));
        menuAdmin.add(crearMenuItem("Método de Alerta"));

        // Ver
        JMenu menuVer = new JMenu("Ver");
        menuVer.add(crearMenuItem("Datos por sensor"));
        menuVer.add(crearMenuItem("Entorno"));
        menuVer.add(crearMenuItem("Alertas"));

        // Ayuda
        JMenu menuAyuda = new JMenu("Ayuda");
        menuAyuda.add(crearMenuItem("Bienvenido"));
        menuAyuda.add(crearMenuItem("Acerca de"));

        menuBar.add(menuInicio);
        menuBar.add(menuAdmin);
        menuBar.add(menuVer);
        menuBar.add(menuAyuda);

        return menuBar;
    }

    private JMenuItem crearMenuItem(String nombre) {
        JMenuItem item = new JMenuItem(nombre);
        item.addActionListener(e -> {
            if (nombre.equals("Salir")) {
                System.exit(0);
            } else if (nombre.equals("Cerrar")) {
                this.dispose(); // Cierra el frame actual
                LoginUI.main(null);
                // Aquí podrías volver al login
            } else {
                mostrarFormulario(nombre);
            }
        });
        return item;
    }

    private void mostrarFormulario(String nombre) {
        if (paneles.containsKey(nombre)) {
            cardLayout.show(mainPanel, nombre);
        } else {
            JOptionPane.showMessageDialog(this, "Formulario no disponible: " + nombre);
        }
    }
}
