package UI;

import Modelos.Alerta;
import Servicios.AlertaService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PanelAlertas extends JPanel {
    private final DefaultListModel<String> modelo;
    private final JList<String> lista;

    public PanelAlertas(String s) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Alertas activas"));

        modelo = new DefaultListModel<>();
        lista = new JList<>(modelo);

        JScrollPane scroll = new JScrollPane(lista);
        add(scroll, BorderLayout.CENTER);
    }

    @Override
    public void addNotify() {
        super.addNotify(); // se ejecuta cuando el panel se vuelve visible
        cargarAlertas();
    }

    private void cargarAlertas() {
        modelo.clear();
        try {
            AlertaService alertaService = new AlertaService();
            List<Alerta> alertas = alertaService.verificarAlertas();

            if (alertas.isEmpty()) {
                modelo.addElement("No hay alertas activas.");
            } else {
                for (Alerta alerta : alertas) {
                    modelo.addElement(alerta.getMensaje());
                }
            }
        } catch (SQLException e) {
            modelo.addElement("Error al cargar alertas: " + e.getMessage());
        }
    }
}
