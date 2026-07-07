package UI.Reportes;

import Servicios.ExportadorCSV;
import Servicios.ExportadorPDF;

import javax.swing.*;
import java.awt.*;

public class PanelExportar extends JPanel {

    private JComboBox<String> comboReporte;
    private JComboBox<String> comboFormato;
    private JButton botonExportar;

    public PanelExportar() {

        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel titulo = new JLabel("Exportación de Reportes");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(30));

        //===========================
        // REPORTE
        //===========================

        JLabel lblReporte = new JLabel("Seleccione el reporte:");

        comboReporte = new JComboBox<>();

        comboReporte.addItem("Alumnos");
        comboReporte.addItem("Profesores");
        comboReporte.addItem("Grupos");

        comboReporte.setMaximumSize(new Dimension(300, 35));

        panel.add(lblReporte);
        panel.add(Box.createVerticalStrut(5));
        panel.add(comboReporte);

        panel.add(Box.createVerticalStrut(20));

        //===========================
        // FORMATO
        //===========================

        JLabel lblFormato = new JLabel("Formato:");

        comboFormato = new JComboBox<>();

        comboFormato.addItem("PDF");
        comboFormato.addItem("CSV");

        comboFormato.setMaximumSize(new Dimension(300, 35));

        panel.add(lblFormato);
        panel.add(Box.createVerticalStrut(5));
        panel.add(comboFormato);

        panel.add(Box.createVerticalStrut(35));

        //===========================
        // BOTÓN
        //===========================

        botonExportar = new JButton("Exportar");

        botonExportar.putClientProperty("JButton.arc", 20);
        botonExportar.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonExportar.addActionListener(e -> exportar());

        panel.add(botonExportar);

        add(panel);

    }

    //=====================================================
    // EXPORTAR
    //=====================================================

    private void exportar() {

        String reporte = (String) comboReporte.getSelectedItem();
        String formato = (String) comboFormato.getSelectedItem();

        try {

            if ("PDF".equals(formato)) {

                switch (reporte) {

                    case "Alumnos":
                        ExportadorPDF.exportarAlumnos();
                        break;

                    case "Profesores":
                        ExportadorPDF.exportarProfesores();
                        break;

                    case "Grupos":
                        ExportadorPDF.exportarGrupos();
                        break;

                    default:
                        JOptionPane.showMessageDialog(
                                this,
                                "Reporte no válido."
                        );
                        break;
                }

            } else if ("CSV".equals(formato)) {

                switch (reporte) {

                    case "Alumnos":
                        ExportadorCSV.exportarAlumnos();
                        break;

                    case "Profesores":
                        ExportadorCSV.exportarProfesores();
                        break;

                    case "Grupos":
                        ExportadorCSV.exportarGrupos();
                        break;

                    default:
                        JOptionPane.showMessageDialog(
                                this,
                                "Reporte no válido."
                        );
                        break;
                }

            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al exportar:\n\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();

        }

    }

}